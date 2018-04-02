package genetic.algorithms.crossover

import genetic.models.{Creature, Population}

/**
  * Expand given population to target population size (inferred from selectionRate) using crossover function.
  * Crossover function for given two parent genes produce new offspring gene as a combination of both parents.
  * The parents combination algorithm is implemented in inherited classes as different crossover strategies.
  */
trait Crossover {
  def crossover[Chromosome](parent1: Creature[Chromosome],
                            parent2: Creature[Chromosome]): Creature[Chromosome]

  def crossover[Chromosome](selectedPopulation: Population[Chromosome],
                            additionPopulationSize: Int): Population[Chromosome]

  def crossover[Chromosome](selectedPopulation: Population[Chromosome],
                            selectionRate: Double): Population[Chromosome] = {
    val additionPopulationSize = expandedPopulationSize(selectedPopulation.size, selectionRate) - selectedPopulation.size
    crossover(selectedPopulation, additionPopulationSize)
  }

  private def expandedPopulationSize(selectedPopulationSize: Int,
                                selectionRate: Double): Int = {
    (selectedPopulationSize / selectionRate).toInt
  }
}
