package e03
import java.io.PrintStream


/* Features:
 * - braces not necessary if there is only single expression
 * - object defines a new class and acts as its singleton instance
 * - object is an instance thus it may be passed as a parameter
 * - class parameters, calling the superclass
 */

class Logger(val where: PrintStream):
	def log(msg: String): Unit =
		where.println(msg)


/* ASSIGNMENT:
 * Change the AppLogger object to print: ">> " + msg
 */
object AppLogger extends Logger(Console.out):
	override def log(msg: String): Unit =
		where.println(">> " + msg)


object LoggingExample:
	def doSomething(logger: Logger): Unit =
		logger.log("I'm doing something")

	def main(args: Array[String]): Unit =
		AppLogger.log("Hello world")
		
		doSomething(AppLogger)
