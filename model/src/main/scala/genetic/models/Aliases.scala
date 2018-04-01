package genetic.models

object Aliases {
  type Fitness = Double
  type FitnessFunction[Chromosome] = Seq[Chromosome] => Fitness
}
