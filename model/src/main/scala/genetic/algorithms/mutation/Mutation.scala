package genetic.algorithms.mutation

import genetic.models.{Creature, Population}

import scala.util.Random

/**
  * Transform given population by performing mutation on individual genes with probability of mutationRate.
  * Mutation function for given gene make a slightly random change on his chromosomes.
  * Chromosomes changing algorithm is implemented in inherited classes as different mutation strategies.
  */
trait Mutation {
  def mutate[Chromosome](creature: Creature[Chromosome]): Creature[Chromosome]

  def mutate[Chromosome](population: Population[Chromosome], mutationRate: Double): Population[Chromosome] = {
    val mutatedGenes = population.creatures.map(creature => {
      if(Random.nextDouble() < mutationRate) {
        mutate(creature)
      } else {
        creature
      }
    })
    population.copy(creatures = mutatedGenes)
  }
}

