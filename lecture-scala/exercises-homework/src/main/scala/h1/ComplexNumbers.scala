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

	def + (c : Complex) = Complex(real + c.real, imaginary + c.imaginary)
	def * (c : Complex) = Complex(real * c.real - imaginary * c.imaginary, real * c.imaginary + imaginary * c.real)

object Complex:
	given Conversion[Int, Complex] = _i => new Complex(_i, 0)

object ComplexOps:
	object I:
		def * (c: Complex) = Complex(0, 1) * c

	extension (lhs: Complex)
		def * (dummy: I.type) = lhs * Complex(0, 1)

object ComplexNumbers:
	def main(args: Array[String]): Unit =
		import Complex.given //it needs to be imported...
		import ComplexOps.* //it needs to be imported...

		println(Complex(1,2)) // 1+2i

		println(1 + 2*I + I*3 + 2) // 3+5i

		val c = (2+3*I + 1 + 4*I) * I
		println(-c) // 7-3i
