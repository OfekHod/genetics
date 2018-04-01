package genetic

import genetic.algorithms.GeneticAlgorithm
import genetic.algorithms.crossover.Crossover
import genetic.algorithms.mutation.Mutation
import genetic.algorithms.selection.Selection
import genetic.models.Population
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSpec, Matchers}

class GeneticAlgorithmSpec extends FunSpec with Matchers with GeneratorDrivenPropertyChecks {

  describe("Basic logic") {
    it("Basic test") {
      object AAA extends Selection {
        override def select[Chromosome](population: Population[Chromosome],
                                        selectionRate: Double): Population[Chromosome] = {
          null
        }
      }

      val geneticParams = new GeneticAlgorithmParameters {
        override def populationSize: Int = 100

        override def selectionAlgorithm: Selection = AAA

        override def mutationAlgorithm: Mutation = null

        override def crossoverAlrorithm: Crossover = null

        override def selectionRate: Double = 0.25

        override def mutationRate: Double = 0.1
      }

      val population = Population.random[Double](geneticParams.populationSize)
      val lastOffspring = GeneticAlgorithm.run(population, 100, geneticParams)
      println(s"final average fitness: ${lastOffspring.averageFitness}.")
    }
  }
}