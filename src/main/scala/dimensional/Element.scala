package dimensional

import dimensional.Element.elem


abstract class Element {
  def contents: Array[String]
  def height: Int = contents.length
  def width: Int = if(contents.length == 0) 0 else contents(0).length

  def above(that: Element): Element = {
    val this1 = this widen that.width
    val that1 = that widen this.width
    elem(this1.contents ++ that1.contents)
  }

  def beside(that: Element): Element = {
    val this1 = this heighten that.height
    val that1 = that heighten this.height
    elem(
      for(
        (line1, line2) <- this1.contents zip that1.contents
      ) yield line1 + line2
    )
  }

  def widen(w: Int): Element = {
    if(w <= width) this
    else {
      val left = elem(' ', (w - width)/2, height)
      val right = elem(' ', w - (left.width + width), height)
      left beside this beside right
    }
  }

  def heighten(h: Int): Element = {
    if(h <= height) this
    else {
      val top = elem(' ', width, (h-height)/2)
      val botton = elem(' ', width, h - (top.height + height))
      top above this above botton
    }
  }

  override def toString: String = contents mkString "\n"
}

object Element {
  def elem(contents: Array[String]): Element = {
    new ArrayElement(contents)
  }

  def elem(s: String): Element = {
    new LineElement(s)
  }

  def elem(ch: Char, width: Int, height: Int): Element = {
    new UniformElement(ch, width, height)
  }

  private class ArrayElement (val contents: Array[String]) extends Element

  private class LineElement(s:String) extends Element {
    def contents: Array[String] = Array(s)
    override val width: Int = s.length
    override val height: Int = 1
  }

  private class UniformElement(
                                val ch: Char,
                                override val width: Int,
                                override val height: Int) extends Element {
    private val line = ch.toString * width
    override val contents: Array[String] = Array.fill(height)(line)
  }
}
