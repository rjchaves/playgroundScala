import scala.collection.mutable

object Main extends App {
  val greetings = new Array[String](1)
  greetings(0) = "teste"
  greetings.update(0, "teste")

  val twoThree = List( 2, 3)
  val oneTwoThree = 1 :: twoThree
  val oneTwo = 1 :: 2 :: 3 :: Nil

  for (greet <- greetings)
    println(greet)

  val l = (1, 2)
  l._1
  var s = Set(1, 2)
  s += 3

  val s2 = mutable.Set(1, 2)
  s2 += 3

  val name = "reader"
  println(s"Hello $name")
  println(s"Hello $name")
  println(
    s"""|Hello
       |$name""".stripMargin.trim)
  val i:BigInt = null;
  println(i + 2)
}
