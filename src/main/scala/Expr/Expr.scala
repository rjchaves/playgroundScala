package Expr

import dimensional.Element
import dimensional.Element.elem

abstract class Expr
case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnOp(op: String, arg: Expr) extends Expr
case class BinOp(op: String, arg1: Expr, arg2: Expr) extends Expr


object Expr {
  def simplifyTop(expr: Expr): Expr = expr match {
    case UnOp("-", UnOp("-", e)) => e
    case BinOp("+", e, Number(0)) => e
    case BinOp("*", e, Number(1)) => e
    case _ => expr
  }
}

class ExprFormatter {
  private val opGroups = Array(
    Set("|", "||"),
    Set("&", "&&"),
    Set("^"),
    Set("==", "!="),
    Set("<", "<=", ">=", ">"),
    Set("+", "-"),
    Set("*", "%")
  )

  private val precedence = {
    val assocs = {
      for {
        i <- opGroups.indices
        op <- opGroups(i)
      } yield op -> i
    }
    assocs.toMap
  }
  private val unaryPrecedence = opGroups.length
  private val fractionPrecedence = -1

  private def format(e: Expr, enclPrec: Int): Element =
    e match {
      case Var(name) => elem(name)
      case Number(num) =>
        def stripDot(s: String) =
          if (s endsWith ".0") s.substring(0, s.length - 2)
          else s
        elem(stripDot(num.toString))
      case UnOp(op, arg) =>
        elem(op) beside format(arg, unaryPrecedence)
      case BinOp("/", left, right) =>
        val top = format(left, fractionPrecedence)
        val bot = format(right, fractionPrecedence)
        val line = elem('-', top.width max bot.width, 1)
        val frac = top above line above bot
        if (enclPrec != fractionPrecedence) frac
        else elem(" ") beside frac beside elem(" ")
      case BinOp(op, left, right) =>
        val opPrec = precedence(op)
        val l = format(left, opPrec)
        val r = format(right, opPrec)
        val oper = l beside elem(" " + op + " ") beside r
        if (enclPrec <= opPrec) oper
        else elem("(") beside oper beside elem(")")
    }
  def format(e: Expr):Element = format(e, 0)
}