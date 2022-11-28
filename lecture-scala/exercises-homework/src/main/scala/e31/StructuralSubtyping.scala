package e31

import reflect.Selectable.reflectiveSelectable

class Logger(printer : { def println(msg: String): Unit}): //printer has the `println` function defined
	def log(msg: String): Unit =
		printer.println(msg)


object ConsoleLogger:
	def println(msg: String): Unit =
		Console.println(msg)


object StructuralSubtyping:
	
	def main(args: Array[String]): Unit =
		val logger = new Logger(ConsoleLogger)
		val logger2 = new Logger(System.out) //this works as well

		logger.log("Hello world!")
		logger2.log("Yo");
