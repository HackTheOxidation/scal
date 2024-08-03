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

def getDaysAsLists(date: LocalDate = LocalDate.now): (String, List[List[String]]) =
  val dayOfWeek = LocalDate.of(date.getYear, date.getMonth, 1).getDayOfWeek.getValue
  val spacing = List.fill(dayOfWeek - 1)("  ")
  val header = s"${date.getMonth} ${date.getYear}"
  (s"$header${" " * (weekDaysAsString.length - header.length)}",
    (spacing ++ (1 to getNumberOfDays(date.getMonthValue)).map(padNumber))
      .sliding(7, 7)
      .toList)

def formatCalendarMonths(date: LocalDate = LocalDate.now, monthsBefore: Int = 0, monthsAfter: Int = 0): String =
  val (header, daysList) = getDaysAsLists(date)
  val daysAsString = daysList
      .map(_.mkString(" "))
      .mkString("\n")

  s"$header\n$weekDaysAsString\n$daysAsString"

@main
def main(args: String*): Unit =
  args.toList match
    case List() => println(formatCalendarMonths())
    case List(_*) => println()