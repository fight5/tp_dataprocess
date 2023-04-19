package com.github.polomarcus.model

case class CO2Record (year: Int,
                      month: Int,
                      ppm: Double
                ) {

  def show() : String = {
    s"""
       |CO2Record from $year/$month :
       |$ppm ppm
       |""".stripMargin
  }

  def isValidPpmValue: Boolean = {
    ppm > 0
  }
}

object CO2Record{
  def isValidPpmValue(ppm: Double): Boolean = {
    ppm > 0
  }
}