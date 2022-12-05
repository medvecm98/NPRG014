"Hello world!" println //create object "Hello world" and send a message println to it

("Hello" .. " " .. "all!") println

helloMsg := "Hello universe!" //assigns a slot called 'helloMsg' on "global object" (implicit receiver, represent some king of global namespace), syntactic sugar for line 7

setSlot("helloMsg", "Hello universe!")
updateSlot("helloMsg", "Hello universe!")

helloMsg println //syntactic sugar for line 11
getSlot("helloMsg") println //"Hello universe!" is now printed two times

printHello := method(name, //method is a message as well, in this case sent to global object, since there is no receiver (to the left side)
	"Hello #{name}!" interpolate println
)

printHello("User")