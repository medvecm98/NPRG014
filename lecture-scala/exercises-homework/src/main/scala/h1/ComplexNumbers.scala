package h1
import scala.language.implicitConversions

// Add necessary class and object definitions in order to make the statements in the main work.

class Complex(val real: Int, val imaginary: Int):

	override def toString() = 
		if (imaginary >= 0)
			s"${real}+${imaginary}i"
		else
			s"${real}${imaginary}i"

	def unary_- = new Complex(-real, -imaginary)

	def + (r : Int) = Complex(real + r, imaginary)
	def + (c : Complex) = Complex(real + c.real, imaginary + c.imaginary)

object ComplexOps:
	object I:
		def * (i: Int) = Complex(0, i)

	extension (lhs: Int)
		def * (dummy: I.type) = Complex(0, lhs)
		def + (rhs: Complex) = Complex(lhs + rhs.real, rhs.imaginary)

	extension (lhs: Complex)
		def * (dummy: I.type) = Complex(-lhs.imaginary, lhs.real)

object ComplexNumbers:
	def main(args: Array[String]): Unit =
		import ComplexOps.* //it needs to be imported...
		println(Complex(1,2)) // 1+2i
		/**/
		println(1 + 2*I + I*3 + 2) // 3+5i
		/**/
		val c = (2+3*I + 1 + 4*I) * I
		println(-c) // 7-3i
		/**/