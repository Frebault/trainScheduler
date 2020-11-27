package app

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class TrainSpec extends AnyFlatSpec with Matchers {
  "Train" should "have at least 2 stations in the schedule" in {
    val bavarianTrain = BavarianRegional(12)
    an[IllegalArgumentException] should be thrownBy Train(bavarianTrain, Seq())
  }

  "Train.stations" should "be the Seq of stations in the schedule" in {
    val bavarianTrain = BavarianRegional(12)
    val train = Train(bavarianTrain, Seq((Time(), Station("Paris")), (Time(2, 10), Station("London"))))
    train.stations shouldBe Seq(Station("Paris"), Station("London"))
  }

  "timeAt" should "Return Some Time if the Train stops at the station" in {
    val bavarianTrain = BavarianRegional(12)
    val train = Train(bavarianTrain, Seq((Time(), Station("Paris")), (Time(2, 10), Station("London"))))
    train.timeAt(Station("Paris")) shouldBe Some(Time())
  }

  "timeAt" should "Return None if the Train does not stop at the station" in {
    val bavarianTrain = BavarianRegional(12)
    val train = Train(bavarianTrain, Seq((Time(), Station("Paris")), (Time(2, 10), Station("London"))))
    train.timeAt(Station("Athens")) shouldBe None
  }
}
