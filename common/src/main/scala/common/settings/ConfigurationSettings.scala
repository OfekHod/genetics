package common.settings

import com.typesafe.config.ConfigFactory
import scala.collection.mutable.ListBuffer
import org.apache.log4j.Logger

/**
  * Creates settings products from application.conf.
  * Includes validation of settings values for each product.
  * Call initialize() in order to trigger objects creation and validation.
  * Recommended to initialize as first thing on main class.
  * Do not support creation of new products outside this object.
  */
object ConfigurationSettings {
  private val config = ConfigFactory.load()
  private val pathPrefix = "common"
  private val logger = Logger.getLogger(this.getClass)

  private val productNames = new ListBuffer[String]()

  def initialize(): Unit = {
    logger.debug(s"Initialized ConfigurationSettings with products: ${productNames.mkString(", ")}.")
  }

  val calculator = createProduct(CalculatorSettings)

  private def createProduct[Product](productClass: Settings[Product]): Product = {
    val name = productClass.name
    val product = productClass.fromConf(config.getConfig(s"$pathPrefix.$name"))
    productClass.validate(product)
    productNames += name

    product
  }
}
