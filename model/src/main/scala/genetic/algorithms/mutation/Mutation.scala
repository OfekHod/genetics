package genetic.algorithms.mutation

import genetic.models.{Gene, Population}

import scala.util.Random

trait Mutation {
  def mutate[Chromosome](gene: Gene[Chromosome]): Gene[Chromosome]

  def mutate[Chromosome](population: Population[Chromosome], mutationRate: Double): Population[Chromosome] = {
    val mutatedGenes = population.genes.map(gene => {
      if(Random.nextDouble() < mutationRate) {
        mutate(gene)
      } else {
        gene
      }
    })
    population.copy(genes = mutatedGenes)
  }
}

