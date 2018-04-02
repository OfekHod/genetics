package genetic

import java.util.Random

import genetic.algorithms.GeneticAlgorithm
import genetic.algorithms.crossover.Crossover
import genetic.algorithms.mutation.Mutation
import genetic.algorithms.selection.Selection
import genetic.models.Aliases.{Fitness, FitnessFunction}
import genetic.models.{Creature, CreatureDouble, Population}
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSpec, Matchers}
import org.apache.log4j.Logger

class GeneticAlgorithmSpec extends FunSpec with Matchers with GeneratorDrivenPropertyChecks {
  val logger = Logger.getLogger(this.getClass)

  describe("Basic logic") {
    it("Basic test") {
      object MySelection extends Selection {
        override def select[Chromosome](population: Population[Chromosome], shrinkedPopulationSize: Int): Population[Chromosome] = {
          Population(
            population.creatures.sortBy(-_.fitness).take(shrinkedPopulationSize),
            shrinkedPopulationSize)
        }
      }

      object MyCrossover extends Crossover {
        override def crossover[Chromosome](parent1: Creature[Chromosome], parent2: Creature[Chromosome]): Creature[Chromosome] = {
          val rnd = new Random()
          val crossPoint = rnd.nextInt(parent1.chromosome.length)
          val newChromosome = parent1.chromosome.take(crossPoint) ++ parent2.chromosome.drop(crossPoint)
          parent1.withChromosome(newChromosome)
        }

        override def crossover[Chromosome](selectedPopulation: Population[Chromosome], additionPopulationSize: Int): Population[Chromosome] = {
          val rnd = new Random
          def selectIndividual = selectedPopulation.creatures(rnd.nextInt(selectedPopulation.size))
          val expandedPopulationSize = selectedPopulation.size + additionPopulationSize
          val creatures = Array.fill(selectedPopulation.size + additionPopulationSize){selectIndividual}
          Population(creatures, expandedPopulationSize)
        }
      }

      object MyMutation extends Mutation {
        override def mutate[Chromosome](creature: Creature[Chromosome]): Creature[Chromosome] = {
          val creatureMutateRate = 0.1

          creature match {
            case creatureDouble: CreatureDouble => {
              val rnd = new Random
              val newChromosome = creatureDouble.chromosome.map(gene => {
                if(rnd.nextDouble() < creatureMutateRate) {
                  rnd.nextDouble()
                } else {
                  gene
                }
              })
              creatureDouble.withChromosome(newChromosome)
            }
            case _ => throw new IllegalArgumentException("MyMutation is allowed only on IndividualDouble")
          }

        }
      }

      val myFitnessFunction: FitnessFunction[Double] =
        (chromosome: Seq[Double]) => chromosome.sum

      val geneticParams = new GeneticAlgorithmParameters {
        override def populationSize: Int = 10000

        override def selectionAlgorithm: Selection = MySelection

        override def crossoverAlrorithm: Crossover = MyCrossover

        override def mutationAlgorithm: Mutation = MyMutation

        override def selectionRate: Double = 0.25

        override def mutationRate: Double = 0.1

        override def chromosomeSize: Int = 64

        override def parallelism: Int = 1
      }

      val population = Population.random[Double](
        geneticParams.populationSize,
        CreatureDouble.random(
          geneticParams.chromosomeSize,
          myFitnessFunction))

      logger.debug(geneticParams.stringify)
      val lastOffspring = GeneticAlgorithm.run(population, 10000, geneticParams)


      lastOffspring.averageFitness shouldBe > (population.averageFitness)
    }
  }
}