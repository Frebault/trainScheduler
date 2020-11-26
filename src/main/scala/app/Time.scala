package app

object Time {
  def fromMinutes(minutes: Int): Time = {
    val hours = minutes / 60
    val remainingMinutes = minutes % 60
    Time(hours, remainingMinutes)
  }
}

case class Time(hours: Int = 0, minutes: Int = 0) {
  require(hours >= 0 && hours < 24, "Hour must be within 0 and 23")
  require(minutes >= 0 && minutes < 59, "Minutes must be within 0 and 59")

  val asMinutes: Int = hours * 60 + minutes

  def -(that: Time): Int = minus(that)

  def minus(that: Time): Int = asMinutes - that.asMinutes
}
