package app

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class JourneyPlannerSpec extends AnyFlatSpec with Matchers {
  "JourneyPlanner.stations" should "be all Stations of all Trains" in {
    val bavarianTrain = BavarianRegional(12)
    val train1 = Train(bavarianTrain, Seq((Time(), Station("Paris")), (Time(2, 10), Station("London"))))
    val train2 = Train(bavarianTrain, Seq((Time(), Station("Athens")), (Time(2, 10), Station("Istanbul"))))
    val journeyPlanner = new JourneyPlanner(Set(train1, train2))

    journeyPlanner.stations shouldBe Set(Station("Paris"), Station("London"), Station("Athens"), Station("Istanbul"))
  }

  "trainsAt" should "return all Trains that contain the given station in their stations field" in {
    val bavarianTrain = BavarianRegional(12)
    val train1 = Train(bavarianTrain, Seq((Time(), Station("Paris")), (Time(2, 10), Station("London"))))
    val train2 = Train(bavarianTrain, Seq((Time(), Station("Athens")), (Time(2, 10), Station("Istanbul"))))
    val journeyPlanner = new JourneyPlanner(Set(train1, train2))

    journeyPlanner.trainsAt(Station("Paris")) shouldBe Set(train1)
  }

  "stopsAt" should "return the stops of all trains at the given station" in {
    val bavarianTrain = BavarianRegional(12)
    val train1 = Train(bavarianTrain, Seq((Time(), Station("Paris")), (Time(2, 10), Station("London"))))
    val train2 = Train(bavarianTrain, Seq((Time(), Station("Athens")), (Time(2, 10), Station("Paris"))))
    val journeyPlanner = new JourneyPlanner(Set(train1, train2))

    journeyPlanner.stopsAt(Station("Paris")) shouldBe Set((Time(), train1), (Time(2, 10),train2))
  }

  "isShortTrip" should "return true if there exists a Train in trains where stations contain from and to with 0 Station in between" in {
    val bavarianTrain = BavarianRegional(12)
    val train1 = Train(bavarianTrain, Seq((Time(), Station("Paris")), (Time(2, 10), Station("London"))))
    val train2 = Train(bavarianTrain, Seq((Time(), Station("Athens")), (Time(2, 10), Station("Paris"))))
    val journeyPlanner = new JourneyPlanner(Set(train1, train2))

    journeyPlanner.isShortTrip(Station("Paris"), Station("London")) shouldBe true
  }

  it should "return true if there exists a Train in trains where stations contain from and to with one other Station in between" in {
    val bavarianTrain = BavarianRegional(12)
    val train1 = Train(bavarianTrain, Seq((Time(), Station("Paris")), (Time(2, 10), Station("London")), (Time(3, 10), Station("Oxford"))))
    val train2 = Train(bavarianTrain, Seq((Time(), Station("Athens")), (Time(2, 10), Station("Paris"))))
    val journeyPlanner = new JourneyPlanner(Set(train1, train2))

    journeyPlanner.isShortTrip(Station("Paris"), Station("Oxford")) shouldBe true
  }

  it should "return false if there does not exist a Train in trains where stations contain from and to with at most one other Station in between" in {
    val bavarianTrain = BavarianRegional(12)
    val train1 = Train(bavarianTrain, Seq((Time(), Station("Paris")), (Time(2, 10), Station("London")), (Time(3, 10), Station("Oxford"))))
    val train2 = Train(bavarianTrain, Seq((Time(), Station("Athens")), (Time(2, 10), Station("Paris"))))
    val journeyPlanner = new JourneyPlanner(Set(train1, train2))

    journeyPlanner.isShortTrip(Station("Paris"), Station("Athens")) shouldBe false
  }
}