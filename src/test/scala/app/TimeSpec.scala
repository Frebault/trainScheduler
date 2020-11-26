package app

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class TimeSpec extends AnyFlatSpec with Matchers {
    "Time hours" should "be within 0 and 23" in {

      the [IllegalArgumentException] thrownBy new Time(-1, 0) should have message "requirement failed: Hour must be within 0 and 23"
    }

  "Time default values" should "be 0" in {
    val time = Time()

    time.hours shouldBe 0
    time.minutes shouldBe 0
  }

  "Time companion object" should "return a Time with normalized minutes" in {
    val time = Time.fromMinutes(200)

    time.hours shouldBe 3
    time.minutes shouldBe 20
  }

  "Time asMinute" should "be hours time 60 plus minutes" in {
    val minutes = 25
    Time(5, minutes).asMinutes shouldBe 5 * 60 + minutes
  }

  "Time(1, 10) minus Time(0,10)" should "be 60" in {
    val time1 = Time(1, 10)
    val time2 = Time(0, 10)
    time1 minus time2 shouldBe 60
  }

  "Time(1, 10) - Time(0,10)" should "be 60" in {
    val time1 = Time(1, 10)
    val time2 = Time(0, 10)
    time1 - time2 shouldBe 60
  }
}