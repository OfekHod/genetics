package common.settings

import com.typesafe.config.Config
import org.apache.log4j.Logger

trait Settings[Product] {
  def name: String
  def fromConf(conf: Config): Product
  def validate(p: Product): Unit = {
    val logger = Logger.getLogger(this.getClass)
    logger.debug(s"'$name' settings has been validated successfully from config.")
  }
}
