package genetic

import genetic.algorithms.crossover.Crossover
import genetic.algorithms.mutation.Mutation
import genetic.algorithms.selection.Selection

trait GeneticAlgorithmParameters {
  def populationSize: Int
  def selectionAlgorithm: Selection
  def mutationAlgorithm: Mutation
  def crossoverAlrorithm: Crossover
  def selectionRate: Double
  def mutationRate: Double
}
