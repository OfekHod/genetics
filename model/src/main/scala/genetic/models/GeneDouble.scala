package genetic.models
import java.util.Random

import genetic.models.Aliases.{Fitness, FitnessFunction}

class GeneDouble(chromosome: Seq[Double], fitnessFunction: FitnessFunction[Double]) extends Gene[Double](chromosome, fitnessFunction){
  override def random(chromosomeSize: Int, fitnessFunction: FitnessFunction[Double]): Gene[Fitness] = {
    GeneDouble.random(chromosomeSize, fitnessFunction)
  }
}

object GeneDouble {
  def random(chromosomeSize: Int, fitnessFunction: Seq[Double] => Fitness): GeneDouble = {
    val rnd = new Random()
    val chromosome = Array.fill(chromosomeSize){rnd.nextDouble()}
    new GeneDouble(chromosome, fitnessFunction)
  }
}
