package io.github.hacktheoxidation

import java.time.LocalDate

val weekDays = List("Mo", "Tu", "We", "Th", "Fr", "Sa", "Su")
val weekDaysAsString: String = weekDays.mkString(" ") + " "

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
  val (header, daysList) = ((date.getMonthValue - monthsBefore) to (date.getMonthValue + monthsAfter))
    .map(i => getDaysAsLists(LocalDate.of(date.getYear, i, 1)))
    .reduce((a, b) => ((a._1 + b._1), a._2.zip(b._2).map((a, b) => a.concat(b))))
  val daysAsString = daysList
      .map(_.mkString(" "))
      .mkString("\n")
  val totalNumberOfMonths = 1 + monthsBefore + monthsAfter

  s"$header\n${weekDaysAsString * totalNumberOfMonths}\n$daysAsString"

@main
def main(args: String*): Unit =
  args.toList match
    case List() => println(formatCalendarMonths(LocalDate.now, 1, 1))
    case List(_*) => println()