package genetic.models

import genetic.GeneticAlgorithmParameters
import genetic.models.Aliases.Fitness

import scala.collection.parallel.immutable.ParSeq

case class Population[Chromosome](creatures: ParSeq[Creature[Chromosome]], size: Int) {
  def this(creatures: ParSeq[Creature[Chromosome]]) {
    this(creatures, creatures.length)
  }
  lazy val chromosomeSize: Int = creatures.head.chromosome.length
  lazy val averageFitness: Fitness = creatures.map(_.fitness).sum / size
  lazy val leadCreature: Creature[Chromosome] = creatures.maxBy(_.fitness)
}

object Population {
  def random[Chromosome](size: Int, sampleCreature: Creature[Chromosome]): Population[Chromosome] = {
    val creatures = ParSeq.fill(size) {sampleCreature.random()}
    Population[Chromosome](creatures, size)
  }
  def duplicatedCreaturePopulation[Chromosome](size: Int, creature: Creature[Chromosome]): Population[Chromosome] = {
    val creatures = ParSeq.fill(size) {creature}
    Population[Chromosome](creatures, size)
  }
}