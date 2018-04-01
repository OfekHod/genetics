package genetic.models

import genetic.models.Aliases.Fitness

case class Population[Chromosome](genes: Seq[Gene[Chromosome]], size: Int) {
  def this(genes: Seq[Gene[Chromosome]]) {
    this(genes, genes.length)
  }
  lazy val chromosomeSize: Int = genes.head.chromosome.length
  lazy val averageFitness: Fitness = genes.map(_.fitness).sum / size
  lazy val leadGene: Gene[Chromosome] = genes.maxBy(_.fitness)
}

object Population {
  def random[Chromosome](populationSize: Int): Population[Chromosome] = {
    val genes = Array.fill(populationSize) {randomGene[Chromosome]()}
    Population[Chromosome](genes, populationSize)
  }
  def duplicatedGenePopulation[Chromosome](populationSize: Int, gene: Gene[Chromosome]): Population[Chromosome] = {
    val genes = Array.fill(populationSize) {gene}
    Population[Chromosome](genes, populationSize)
  }

  // todo: create static random functino in gene somehow
  private def randomGene[Chromosome](): Gene[Chromosome] = {
    null
  }
}