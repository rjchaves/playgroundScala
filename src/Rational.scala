import scala.annotation.tailrec

//usar implicit def intToRational(x: Int) = new Rational(x) para scala.language.implicitConversions
class Rational (n: Int, d: Int){
  require(d != 0)
  private val g = gcd(n.abs, d.abs)
  val numerator: Int = n / g
  val denominator: Int = d / g


  def this(n: Int)  = this(n, 1)

  def + (that: Rational): Rational =
    new Rational(
      numerator * that.denominator + that.numerator * denominator,
      denominator * that.denominator
    )

  def + (that: Int) : Rational =
    new Rational(
      numerator + that * denominator,
      denominator
    )

  def - (that: Rational) : Rational =
    new Rational(
      this.numerator*that.denominator - that.numerator * this.denominator,
      this.denominator * that.denominator
    )

  def - (that: Int) : Rational =
    new Rational(
      this.numerator - that * denominator,
      denominator
    )

  def * (that: Rational) : Rational =
    new Rational(
      this.numerator * that.numerator,
      this.denominator * that.denominator
    )

  def * (that: Int) : Rational =
    new Rational(
      this.numerator * that,
      this.denominator
    )

  def < (that:Rational): Boolean =
    this.numerator * that.denominator < that.numerator * this.denominator

  def / (that: Rational) : Rational =
    new Rational(
      this.numerator * that.denominator,
      that.denominator * this.denominator
    )

  def / (that : Int) : Rational =
    new Rational(
      this.numerator,
      this.denominator * that
    )

  def max (that:Rational): Rational =
    if(this < that) that else this

  override def toString: String = s"$numerator/$denominator"

  //algoritmo de euclides
  @tailrec
  private def gcd(i: Int, l: Int): Int = {
    if(l == 0) i else gcd(l, i % l)
  }
}
