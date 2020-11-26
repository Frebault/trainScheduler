package app

case class Train(kind: String, number: Int, schedule: Seq[(Time, Station)]) {
  require(schedule.length > 1, "Schedule must have at least 2 elements")

  val stations: Seq[Station] = schedule.map(_._2)
}