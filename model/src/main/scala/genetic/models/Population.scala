package genetic.models

import genetic.models.Aliases.Fitness

case class Population[Chromosome](creatures: Seq[Creature[Chromosome]], size: Int) {
  def this(creatures: Seq[Creature[Chromosome]]) {
    this(creatures, creatures.length)
  }
  lazy val chromosomeSize: Int = creatures.head.chromosome.length
  lazy val averageFitness: Fitness = creatures.map(_.fitness).sum / size
  lazy val leadCreature: Creature[Chromosome] = creatures.maxBy(_.fitness)
}

object Population {
  def random[Chromosome](size: Int, sampleCreature: Creature[Chromosome]): Population[Chromosome] = {
    val creatures = Array.fill(size) {sampleCreature.random()}
    Population[Chromosome](creatures, size)
  }
  def duplicatedCreaturePopulation[Chromosome](size: Int, creature: Creature[Chromosome]): Population[Chromosome] = {
    val creatures = Array.fill(size) {creature}
    Population[Chromosome](creatures, size)
  }
}