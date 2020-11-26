package app

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class StationSpec extends AnyFlatSpec with Matchers{
  "Station" should "be instantiable" in {
    Station("Paris")
  }

}
