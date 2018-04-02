package genetic

import genetic.algorithms.crossover.Crossover
import genetic.algorithms.mutation.Mutation
import genetic.algorithms.selection.Selection

trait GeneticAlgorithmParameters {
  def populationSize: Int

  def selectionAlgorithm: Selection

  def crossoverAlrorithm: Crossover

  def mutationAlgorithm: Mutation

  def selectionRate: Double

  def mutationRate: Double

  def chromosomeSize: Int

  def parallelism: Int

  def stringify: String =
    s"Genetic Algorithm Parameters: " +
      s"Population size: $populationSize, " +
      s"Selection algorithm: $populationSize, " +
      s"Crossover algorithm: $populationSize, " +
      s"Mutation algorithm: $populationSize, " +
      s"Selection rate: $populationSize, " +
      s"Mutation rate: $populationSize, " +
      s"Chromosome size: $populationSize."

}
