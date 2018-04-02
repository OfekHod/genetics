package genetic

import genetic.algorithms.crossover.Crossover
import genetic.algorithms.mutation.Mutation
import genetic.algorithms.selection.Selection
import genetic.models.Aliases.FitnessFunction

trait GeneticAlgorithmParameters[Chromosome] {
  def populationSize: Int

  def selectionAlgorithm: Selection

  def crossoverAlrorithm: Crossover

  def mutationAlgorithm: Mutation

  def selectionRate: Double

  def mutationRate: Double

  def fitnessFunction: FitnessFunction[Chromosome]

  def chromosomeSize: Int

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
