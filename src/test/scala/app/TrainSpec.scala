package app

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class TrainSpec extends AnyFlatSpec with Matchers {
  "Train" should "be instantiable" in {
    Train("TGV",12)
  }
}
