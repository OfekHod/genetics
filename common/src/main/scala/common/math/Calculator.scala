package common.math

import common.settings.CalculatorSettings

object Calculator {

  /**
    * Sum 3 numbers with min/max result boundaries.
    */
  def sum(n1: Int, n2: Int, n3: Int, minRes: Int, maxRes: Int): Int = {
    val sumRes = n1 + n2 + n3
    if (sumRes > maxRes) {
      maxRes
    } else if (sumRes < minRes) {
      minRes
    } else {
      sumRes
    }
  }

  /**
    * Sum 3 numbers with min/max result boundaries.
    * Third number and result boundaries are taken from settings.
    */
  def sum(n1: Int, n2: Int, settings: CalculatorSettings): Int = {
    this.sum(n1, n2, settings.n3, settings.minRes, settings.maxRes)
  }

  /**
    * Subtract 3 numbers with min/max result boundaries.
    */
  def sub(n1: Int, n2: Int, n3: Int, minRes: Int, maxRes: Int): Int = {
    val subRes = n1 - n2 - n3
    if (subRes > maxRes) {
      maxRes
    } else if (subRes < minRes) {
      minRes
    } else {
      subRes
    }
  }

  /**
    * Subtract 3 numbers with min/max result boundaries.
    * Third number and result boundaries are taken from settings.
    */
  def sub(n1: Int, n2: Int, settings: CalculatorSettings): Int = {
    this.sub(n1, n2, settings.n3, settings.minRes, settings.maxRes)
  }
}
