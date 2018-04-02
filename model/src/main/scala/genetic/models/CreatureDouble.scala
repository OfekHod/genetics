package genetic.models
import java.util.Random

import genetic.models.Aliases.FitnessFunction

case class CreatureDouble(override val chromosome: Seq[Double],
                          override val fitnessFunction: FitnessFunction[Double]) extends Creature[Double](chromosome, fitnessFunction){
  override def random(chromosomeSize: Int, fitnessFunction: FitnessFunction[Double]): Creature[Double] = {
    CreatureDouble.random(chromosomeSize, fitnessFunction)
  }

  override def withChromosome(newChromosome: Seq[Double]): Creature[Double] = {
    this.copy(newChromosome, fitnessFunction)
  }
}

object CreatureDouble {
  def random(chromosomeSize: Int, fitnessFunction: FitnessFunction[Double]): CreatureDouble = {
    val rnd = new Random()
    val chromosome = Array.fill(chromosomeSize){rnd.nextDouble()}
    new CreatureDouble(chromosome, fitnessFunction)
  }
}
