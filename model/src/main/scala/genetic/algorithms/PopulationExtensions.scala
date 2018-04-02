package genetic.algorithms

import genetic.GeneticAlgorithmParameters
import genetic.algorithms.crossover.Crossover
import genetic.algorithms.mutation.Mutation
import genetic.algorithms.selection.Selection
import genetic.models.Population

object PopulationExtensions {
  implicit class PopulationExtensions[Chromosome](population: Population[Chromosome]) {
    def select(selectionRate: Double)(implicit selectionStrategy: Selection): Population[Chromosome] = {
      selectionStrategy.select(population, selectionRate)
    }

    def crossover(selectionRate: Double)(implicit crossoverStrategy: Crossover): Population[Chromosome] = {
      crossoverStrategy.crossover(population, selectionRate)
    }
    def mutate(mutationRate: Double)(implicit mutationStrategy: Mutation): Population[Chromosome] = {
      mutationStrategy.mutate(population, mutationRate)
    }
  }
}
