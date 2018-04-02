package genetic.models

import genetic.models.Aliases.FitnessFunction

abstract class Creature[Chromosome](val chromosome: Seq[Chromosome],
                                    val fitnessFunction: FitnessFunction[Chromosome]) {
  lazy val fitness = fitnessFunction(chromosome)

  def random(chromosomeSize: Int, fitnessFunction: FitnessFunction[Chromosome]): Creature[Chromosome]

  def withChromosome(chromosome: Seq[Chromosome]): Creature[Chromosome]

  def random(): Creature[Chromosome] = {
    random(this.chromosome.length, this.fitnessFunction)
  }
}