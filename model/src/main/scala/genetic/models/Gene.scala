package genetic.models

import genetic.models.Aliases.{Fitness, FitnessFunction}

abstract class Gene[Chromosome](val chromosome: Seq[Chromosome],
                                val fitnessFunction: FitnessFunction[Chromosome]) {
  lazy val fitness = fitnessFunction(chromosome)

  def random(chromosomeSize: Int, fitnessFunction: FitnessFunction[Chromosome]): Gene[Chromosome]
}