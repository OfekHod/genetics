package genetic.models

import genetic.models.Aliases.Fitness

trait Gene[Chromosome] {
  def chromosome: Seq[Chromosome]
  def fitness: Fitness
  def random(chromosomeSize: Int): Gene[Chromosome]
}