package app

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class TrainSpec extends AnyFlatSpec with Matchers {
  "Train" should "be instantiable" in {
    Train("TGV", 12, Seq(Station("Paris"), Station("London")))
  }

  "Train" should "have at least 2 stations in the schedule" in {
    an[IllegalArgumentException] should be thrownBy Train("TGV", 12, Seq())
  }
}
