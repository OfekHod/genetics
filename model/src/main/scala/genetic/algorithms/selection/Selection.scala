package genetic.algorithms.selection

import genetic.models.Population

trait Selection {
  def select[Chromosome](population: Population[Chromosome],
                         selectionRate: Double): Population[Chromosome]
}
