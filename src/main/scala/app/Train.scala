package app

case class Train(info: TrainInfo, schedule: Seq[(Time, Station)]) {
  require(schedule.length > 1, "Schedule must have at least 2 elements")

  val stations: Seq[Station] = schedule.map(_._2)

  def timeAt(station: Station): Option[Time] = schedule.find(_._2 == station).map(_._1)
}

sealed abstract class TrainInfo {
  def number: Int
}

case class InterCityExpress(number: Int, hasWifi: Boolean = false) extends TrainInfo

case class RegionalExpress(number: Int) extends TrainInfo

case class BavarianRegional(number: Int) extends TrainInfo