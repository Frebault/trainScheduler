package app

import play.api.libs.json._

import scala.util.Try

object Time {
  def fromMinutes(minutes: Int): Time = {
    val hours = minutes / 60
    val remainingMinutes = minutes % 60
    Time(hours, remainingMinutes)
  }

  def fromJson(file: JsValue): Option[Time] = {
    for {
      hours <- Try((file \ "hours").as[Int])
      minutes <- Try((file \ "minutes").as[Int]).recover { case _: Exception => 0 }
    } yield Time(hours, minutes)
  }.toOption
}

case class Time(hours: Int = 0, minutes: Int = 0) extends Ordered[Time] {
  require(hours >= 0 && hours < 24, "Hour must be within 0 and 23")
  require(minutes >= 0 && minutes < 59, "Minutes must be within 0 and 59")

  override lazy val toString: String = f"$hours%02d:$minutes%02d"
  val asMinutes: Int = hours * 60 + minutes

  override def compare(that: Time): Int = asMinutes - that.asMinutes

  def -(that: Time): Int = minus(that)

  def minus(that: Time): Int = asMinutes - that.asMinutes

  def toJson: JsValue = JsObject(Map("hours" -> JsNumber(hours), "minutes" -> JsNumber(minutes)))
}
