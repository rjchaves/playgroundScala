class Rational (n: Int, d: Int){
  require(d != 0)
  val numerator: Int = n
  val denominator: Int = d


  def this(n: Int)  = this(n, 1)

  def add(that: Rational): Rational = {
    new Rational(
      numerator * that.denominator + that.numerator * denominator,
      denominator * that.denominator
    )
  }

  def +(that: Rational): Rational = add(that)

  def lessThan(that:Rational): Boolean =
    this.numerator * that.denominator < that.numerator * this.denominator

  def <(that:Rational): Boolean = lessThan(that)

  def max(that:Rational): Rational =
    if(this < that) that else this

  override def toString: String = s"$numerator/$denominator"
}
