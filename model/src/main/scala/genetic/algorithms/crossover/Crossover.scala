package genetic.algorithms.crossover

import genetic.models.{Gene, Population}

trait Crossover {
  def crossover[Chromosome](gene1: Gene[Chromosome], gene2: Gene[Chromosome]): Gene[Chromosome]
  def crossover[Chromosome](selectedPopulation: Population[Chromosome], selectionRate: Double): Population[Chromosome] = {
    val newGenesToCross = newPopulationSize(selectedPopulation.size, selectionRate) - selectedPopulation.size
    selectedPopulation
  }
  def newPopulationSize(selectedPopulationSize: Int, selectionRate: Double): Int = {
    ((1 / selectionRate) * selectedPopulationSize).toInt
  }
}
