import com.github.polomarcus.utils.ClimateService
import com.github.polomarcus.model.CO2Record
import jdk.internal.vm.vector.VectorSupport.test
import org.scalatest.funsuite.AnyFunSuite

//@See https://www.scalatest.org/scaladoc/3.1.2/org/scalatest/funsuite/AnyFunSuite.html
class ClimateServiceTest extends AnyFunSuite {
  test("containsWordGlobalWarming - non climate related words should return false") {
    assert( ClimateService.isClimateRelated("pizza") == false)
  }

  test("isClimateRelated - climate related words should return true") {
    assert(ClimateService.isClimateRelated("climate change") == true)
    assert(ClimateService.isClimateRelated("IPCC"))
    assert(ClimateService.isClimateRelated("IPCC"))
  }

  //@TODO
  test("parseRawData") {
    // our inputs
    val firstRecord = (2003, 1, 355.2)     //help: to acces 2003 of this tuple, you can do firstRecord._1
    val secondRecord = (2004, 1, 375.2)
    val list1 = List(firstRecord, secondRecord)

    // our output of our method "parseRawData"
    val co2RecordWithType = CO2Record(firstRecord._1, firstRecord._2, firstRecord._3)
    val co2RecordWithType2 = CO2Record(secondRecord._1, secondRecord._2, secondRecord._3)
    val output = List(Some(co2RecordWithType), Some(co2RecordWithType2))

    // we call our function here to test our input and output
    assert(ClimateService.parseRawData(list1) == output)
  }

  //@TODO
  test("filterDecemberData") {
    val list = List(
      Some(CO2Record(1958, 3, 316.19)),
      Some(CO2Record(1958, 4, 317.29)),
      Some(CO2Record(1958, 5, 317.87)),
      Some(CO2Record(1958, 6, -99.99)),
      Some(CO2Record(1958, 7, 315.85)),
      Some(CO2Record(1958, 8, 313.97)),
      Some(CO2Record(1958, 9, 312.44)),
      Some(CO2Record(1958, 10, -99.99)),
      Some(CO2Record(1958, 11, 313.6)),
      Some(CO2Record(1958, 12, 314.76))
    )

    val expectedResult = List(
      CO2Record(1958, 3, 316.19),
      CO2Record(1958, 4, 317.29),
      CO2Record(1958, 5, 317.87),
      CO2Record(1958, 6, -99.99),
      CO2Record(1958, 7, 315.85),
      CO2Record(1958, 8, 313.97),
      CO2Record(1958, 9, 312.44),
      CO2Record(1958, 10, -99.99),
      CO2Record(1958, 11, 313.6)
    )
    assert(ClimateService.filterDecemberData(list) == expectedResult)
  }
}

  test("getMinMaxByYear") {
    val list = List(
    CO2Record(1958, 3, 316.19),
    CO2Record(1958, 4, 317.29),
    CO2Record(1959, 5, 317.87),
    CO2Record(1959, 6, -99.99),
    CO2Record(1960, 7, 315.85),
    CO2Record(1960, 8, 313.97),
    CO2Record(1960, 9, 312.44),
    CO2Record(1961, 10, -99.99),
    CO2Record(1961, 11, 313.6),
    CO2Record(1961, 12, 314.76)
  )

  val expectedResult = (316.19, 317.29)
  assert(ClimateService.getMinMaxByYear(1958) == expectedResult)
}

test("getMinMaxByYearDifferentYear") {
  val list = List(
    CO2Record(1958, 3, 316.19),
    CO2Record(1958, 4, 317.29),
    CO2Record(1959, 5, 317.87),
    CO2Record(1959, 6, -99.99),
    CO2Record(1960, 7, 315.85),
    CO2Record(1960, 8, 313.97),
    CO2Record(1960, 9, 312.44),
    CO2Record(1961, 10, -99.99),
    CO2Record(1961, 11, 313.6),
    CO2Record(1961, 12, 314.76)
  )

  val expectedResult = (-99.99, 317.87)
  assert(ClimateService.getMinMaxByYear(list, 1959) == expectedResult)
}

  test("differenceMinMaxByYear") {
  val list = List(
    CO2Record(1958, 3, 316.19),
    CO2Record(1958, 4, 317.29),
    CO2Record(1958, 5, 317.87),
    CO2Record(1958, 6, -99.99),
    CO2Record(1958, 7, 315.85),
    CO2Record(1958, 8, 313.97),
    CO2Record(1958, 9, 312.44),
    CO2Record(1958, 10, -99.99),
    CO2Record(1958, 11, 313.6),
    CO2Record(1958, 12, 314.76)
  )

  val expectedResult = 5.03

  assert(ClimateService.differenceMinMaxByYear(list, 1958) == expectedResult)
}