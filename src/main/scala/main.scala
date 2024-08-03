package io.github.hacktheoxidation

import java.time.LocalDate

val weekDays = List("Mo", "Tu", "We", "Th", "Fr", "Sa", "Su")
val weekDaysAsString: String = weekDays.mkString(" ")

def padNumber(num: Int): String =
  if num < 10 then s"0$num" else s"$num"

def getNumberOfDays(month: Int): Int = month match
  case 2 => 28
  case 4 | 6 | 9 | 11 => 30
  case _ => 31

def printCalendar(): Unit =
  val now = LocalDate.now()
  val dayOfWeek = LocalDate.of(now.getYear, now.getMonth, 1).getDayOfWeek.getValue
  val spacing = (1 until dayOfWeek).map(_ => "  ")
  val daysAsString =
    (spacing ++ (1 to getNumberOfDays(now.getMonthValue)).map(padNumber))
      .sliding(7, 7)
      .map(_.mkString(" "))
      .mkString("\n")

  println(s"${now.getMonth} ${now.getYear}")
  println(weekDaysAsString)
  println(daysAsString)

@main
def main(): Unit =
  printCalendar()
