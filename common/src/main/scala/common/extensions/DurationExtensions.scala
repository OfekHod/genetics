package common.extensions

object TimeExtensions {

  implicit class DurationExtensions(duration: java.time.Duration) {
    def pretty(): String = {
      val asNanos = duration.getNano
      val asMillis = asNanos / 1000000
      val asSeconds = duration.getSeconds
      val asMinutes = asSeconds / 60
      val asHours = asMinutes / 60

      if(asHours > 1) {
        s"$asHours hours"
      } else if(asMinutes > 1) {
        s"$asMinutes minutes"
      } else if (asSeconds > 1) {
        s"$asSeconds seconds"
      } else if(asMillis > 1) {
        s"$asMillis millis"
      } else {
        s"$asNanos nanos"
      }
    }
  }

}
