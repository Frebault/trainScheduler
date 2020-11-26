package app

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class TrainSpec extends AnyFlatSpec with Matchers {
  "Train" should "have at least 2 stations in the schedule" in {
    an[IllegalArgumentException] should be thrownBy Train("TGV", 12, Seq())
  }

  "Train.stations" should "be the Seq of stations in the schedule" in {
    val train = Train("TGV", 12, Seq((Time(), Station("Paris")), (Time(2, 10), Station("London"))))
    train.stations shouldBe Seq(Station("Paris"), Station("London"))
  }
}
