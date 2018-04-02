package genetic.algorithms

import java.time.{Duration, Instant}

import genetic.GeneticAlgorithmParameters
import genetic.models.Population
import org.apache.log4j.Logger

object GeneticAlgorithm {
  val logger = Logger.getLogger(this.getClass)

  def run[Chromosome](population: Population[Chromosome],
                      generations: Int, params: GeneticAlgorithmParameters): Population[Chromosome] = {
    import common.extensions.TimeExtensions.DurationExtensions
    val startMilli = Instant.now()
    val lastOffspring = recursiveRun(population, generations, generations, params)

    val endMilli = Instant.now()
    val duration = Duration.between(startMilli, endMilli)

    logger.info(s"Finished generation #$generations, total time: ${duration.pretty()}.")
    logger.info(s"Initial random average fitness: ${population.averageFitness}, final average fitness: ${lastOffspring.averageFitness}.")
    logger.debug(s"Lead creature chromosome: ${lastOffspring.leadCreature.chromosome}")

    lastOffspring
  }

  private def recursiveRun[Chromosome](population: Population[Chromosome],
                                       totalGenerations: Int, generationsLeft: Int, params: GeneticAlgorithmParameters): Population[Chromosome] = {
    val currentGeneration = totalGenerations - generationsLeft + 1
    if (generationsLeft == 0) {
      population
    } else {
      logger.info(s"Generation #$currentGeneration finished, average fitness: ${population.averageFitness}, lead creature fitness: ${population.leadCreature.fitness}")
      logger.debug(s"Generation #$currentGeneration Lead creature chromosome: ${population.leadCreature.chromosome}")
      recursiveRun(
        createOffspring(
          population,
          params),
        totalGenerations,
        generationsLeft - 1,
        params)
    }
  }

  private def createOffspring[Chromosome](population: Population[Chromosome],
                                          params: GeneticAlgorithmParameters): Population[Chromosome] = {
    import PopulationExtensions.PopulationExtensions

    implicit val selectionStrategy = params.selectionAlgorithm
    implicit val crossoverStratgy = params.crossoverAlrorithm
    implicit val mutationStrategy = params.mutationAlgorithm

    population
      .select(params.selectionRate)
      .crossover(params.selectionRate)
      .mutate(params.mutationRate)
  }
}
