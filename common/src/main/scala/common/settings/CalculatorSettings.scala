package common.settings

import com.typesafe.config.Config
import org.apache.log4j.Logger

case class CalculatorSettings(n3: Int,
                              minRes: Int,
                              maxRes: Int)

object CalculatorSettings extends Settings[CalculatorSettings] {
  private val logger = Logger.getLogger(this.getClass)
  override def name: String = "calculator"

  override def fromConf(conf: Config): CalculatorSettings = {
        CalculatorSettings(
          conf.getInt("n3"),
          conf.getInt("minRes"),
          conf.getInt("maxRes")
        )
  }

  override def validate(calc: CalculatorSettings): Unit = {
    logger.debug(s"$name settings validation started.")
    if (calc.minRes > calc.maxRes) {
      throw new ExceptionInInitializerError("minRes can't be greater than maxRes.")
    }
    super.validate(calc)
  }
}