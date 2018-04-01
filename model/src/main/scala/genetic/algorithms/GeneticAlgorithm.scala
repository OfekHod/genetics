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
