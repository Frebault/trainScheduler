package app

case class Train(kind: String, number: Int, schedule: Seq[Station]) {
  require(schedule.length > 1, "Schedule must have at least 2 elements")
}