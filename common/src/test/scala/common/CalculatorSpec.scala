package common

import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSpec, Matchers}
import common.math.Calculator
import common.settings.CalculatorSettings

class CalculatorSpec extends FunSpec with Matchers with GeneratorDrivenPropertyChecks {

  describe("Calculator functions are bounded at top by maxRes and at bottom by minRes") {
    it("sum function returns sum of 3 numbers bounded with min/max Res") {
      forAll { (n1: Int, n2: Int, n3: Int, minRes: Int, maxRes: Int) =>
        whenever(minRes <= maxRes) {
          val currSettings = CalculatorSettings(
            n3 = n3,
            minRes = minRes,
            maxRes = maxRes
          )
          val sum = n1 + n2 + n3
          val res = Calculator.sum(n1, n2, currSettings)
          if (sum <= currSettings.minRes) {
            res shouldBe currSettings.minRes
          } else if (sum >= currSettings.maxRes) {
            res shouldBe currSettings.maxRes
          } else {
            res shouldBe sum
          }
        }
      }
    }

    it("sub function returns subtract of 3 numbers bounded with min/max Res") {
      forAll { (n1: Int, n2: Int, n3: Int, minRes: Int, maxRes: Int) =>
        whenever(minRes <= maxRes) {
          val currSettings = CalculatorSettings(
            n3 = n3,
            minRes = minRes,
            maxRes = maxRes
          )
          val sub = n1 - n2 - n3
          val res = Calculator.sub(n1, n2, currSettings)
          if (sub <= currSettings.minRes) {
            res shouldBe currSettings.minRes
          } else if (sub >= currSettings.maxRes) {
            res shouldBe currSettings.maxRes
          } else {
            res shouldBe sub
          }
        }
      }
    }
  }
}