package genetic

import java.util.Random

import genetic.algorithms.GeneticAlgorithm
import genetic.algorithms.crossover.Crossover
import genetic.algorithms.mutation.Mutation
import genetic.algorithms.selection.Selection
import genetic.models.Aliases.FitnessFunction
import genetic.models.{Creature, CreatureDouble, Population}
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSpec, Matchers}
import org.apache.log4j.Logger

import scala.collection.parallel.immutable.ParSeq


class GeneticAlgorithmSpec extends FunSpec with Matchers with GeneratorDrivenPropertyChecks {
  val logger = Logger.getLogger(this.getClass)

  describe("Basic logic") {
    it("Basic test") {
      object MySelection extends Selection {
        override def select[Chromosome](population: Population[Chromosome], shrinkedPopulationSize: Int): Population[Chromosome] = {
          Population(
            population.creatures.toList.sortBy(-_.fitness).take(shrinkedPopulationSize).par,
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
          val creatures = ParSeq.fill(selectedPopulation.size + additionPopulationSize){selectIndividual}
          Population(creatures, expandedPopulationSize)
        }
      }

      object zeroToOneMutation extends Mutation {
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

      object LimitlessMutation extends Mutation {
        override def mutate[Chromosome](creature: Creature[Chromosome]): Creature[Chromosome] = {
          val creatureMutateRate = 0.1

          creature match {
            case creatureDouble: CreatureDouble => {
              val rnd = new Random
              val newChromosome = creatureDouble.chromosome.map(gene => {
                if(rnd.nextDouble() < creatureMutateRate) {
                  val positive = rnd.nextDouble()
                  if(positive < 0.5) {
                    gene + gene * positive
                  } else {
                    gene - gene * positive
                  }
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

      val sumFitness: FitnessFunction[Double] =
        (chromosome: Seq[Double]) => chromosome.sum

      val eq1Fitness: FitnessFunction[Double] =
        (x: Seq[Double]) => {
          -1*Math.pow(x(0),2) +20*x(0) + 300
        }

      val eq2Fitness: FitnessFunction[Double] =
        (x: Seq[Double]) => {
          -90*Math.pow(x(0), 6) + 130*Math.pow(x(0), 5) + 9*Math.pow(x(0), 4) + 4*Math.pow(x(0), 3) + 2*Math.pow(x(0), 2) + 8*Math.pow(x(0), 1) + 120
        }

      val eq3Fitness: FitnessFunction[Double] =
        (x: Seq[Double]) => {
          -1*Math.pow(x(0), 2) - 20
        }


      val geneticParams = new GeneticAlgorithmParameters[Double] {
        override def populationSize: Int = 100

        override def selectionAlgorithm: Selection = MySelection

        override def crossoverAlrorithm: Crossover = MyCrossover

        override def mutationAlgorithm: Mutation = LimitlessMutation

        override def selectionRate: Double = 0.25

        override def mutationRate: Double = 0.1

        override def fitnessFunction: FitnessFunction[Double] = eq3Fitness

        override def chromosomeSize: Int = 1
      }

      val population = Population.random[Double](
        geneticParams.populationSize,
        CreatureDouble.random(
          geneticParams.chromosomeSize,
          geneticParams.fitnessFunction))

      logger.debug(geneticParams.stringify)
      val lastOffspring = GeneticAlgorithm.run(population, 500, geneticParams)


      lastOffspring.averageFitness shouldBe > (population.averageFitness)
    }
  }
}