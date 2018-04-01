package genetic.algorithms

import genetic.GeneticAlgorithmParameters
import genetic.models.Population

object GeneticAlgorithm {
  def run[Chromosome](population: Population[Chromosome],
                               generations: Int, params: GeneticAlgorithmParameters): Population[Chromosome]  = {
    if(generations == 0) {
      population
    } else {
      run(
        createOffspring(
          population,
          params),
        generations - 1,
        params)
    }
  }

  private def createOffspring[Chromosome](population: Population[Chromosome],
                                          params: GeneticAlgorithmParameters): Population[Chromosome] = {
    // todo: make select, crossover and mutate work as implicit classes and implicit strategy vals
    implicit val selectionStrategy = params.selectionAlgorithm
    population
      //.select(params.selectionRate)
      //.crossover(selectionRate)
      //.mutate(mutationRate)
  }
}
