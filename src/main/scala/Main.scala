import dimensional.Spiral.spiral

import scala.annotation.tailrec

object Main extends App {
//  val greetings = new Array[String](1)
//  greetings(0) = "teste"
//  greetings.update(0, "teste")
//
//  val twoThree = List( 2, 3)
//  val oneTwoThree = 1 :: twoThree
//  val oneTwo = 1 :: 2 :: 3 :: Nil
//
//  for (greet <- greetings)
//    println(greet)
//
//  val l = (1, 2)
//  l._1
//  var s = Set(1, 2)
//  s += 3
//
//  val s2 = mutable.Set(1, 2)
//  s2 += 3
//
//  val name = "reader"
//  println(s"Hello $name")
//  println(s"Hello $name")
//  println(
//    s"""|Hello
//       |$name""".stripMargin.trim)
//
//
//
//  def filelines(file: File) =
//    Source.fromFile(file).getLines().toArray
//
//  val filesHere = new File("./src").listFiles()
//
//  def grep(pattern: String) = {
//    for(
//      file <- filesHere
//      if file.getName.endsWith(".scala");
//      line <- filelines(file);
//      lineTrimmed = line.trim
//      if lineTrimmed.matches(pattern)
//    ) println(s"$file: ${lineTrimmed}")
//  }
//  grep(".*gcd.*")
//
//
//  def echo(args : String*) = for(s <- args) println(s)
//
//  val seq = Seq("what's", "up")
//  echo(seq: _*)
//
//  def withPrintWriter(file: File)(op: PrintWriter => Unit) = {
//    val writer = new PrintWriter(file)
//    try {
//      op(writer)
//    } finally {
//      writer.close();
//    }
//  }
//
//  val file = new File("data.txt");
//  withPrintWriter(file){ writer =>
//    writer.println(new Date())
//  }

//  def isort(xs: List[Int]) : List[Int] =
//    if(xs.isEmpty) Nil
//    else insert(xs.head, isort(xs.tail))
//
//  def insert(x: Int, xs: List[Int]) : List[Int] =
//    if(xs.isEmpty || x <= xs.head) x :: xs
//    else xs.head :: insert(x, xs.tail)

  def insert(x: Int, xs: List[Int]): List[Int] =
    xs match {
      case List() => List(x)
      case y :: ys => if (x <= y) x :: xs
                      else y :: insert(x, xs)
    }

  def isort(xs: List[Int]): List[Int] =
    xs match {
      case List()  => List()
      case x :: xs => insert(x, isort(xs))
    }

  println(spiral(200, 1))
}
