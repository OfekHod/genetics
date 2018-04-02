package genetic.algorithms.selection

import genetic.models.Population

/**
  * Shrinking given population by performing fitness wise selection.
  * New population size is inferred from selectionRate.
  * Shrinking algorithm is implemented in inherited classes as different selection strategies.
  */
trait Selection {
  def select[Chromosome](population: Population[Chromosome],
                         selectionRate: Double): Population[Chromosome] = {

    val targetPopulationSize = shrinkedPopulationSize(population.size, selectionRate)
    select(population, targetPopulationSize)
  }

  def select[Chromosome](population: Population[Chromosome],
                         shrinkedPopulationSize: Int): Population[Chromosome]

  private def shrinkedPopulationSize(populationSize: Int, selectionRate: Double): Int = {
    (populationSize * selectionRate).toInt
  }
}
