Vect := Object clone do ( //clones object "Object" and you send a message "do" with parameter that is that block of code there
	x := 0
	y := 0
	length := method( (x squared + y squared) sqrt )
)

v1 := Vect clone //slot v1 in global object
v1 x = 3
v1 y = 4

v1 println //lookup starts from receiver of the message
"Length of v1 is #{v1 length}" interpolate println


"" println


/* ASSIGNMENT:
 * Override the asString method such that println prints out the vector in form: "v(3,4)"
 */

Vect asString := method( "v(#{x},#{y})" interpolate )

v1 println // prints "v(3,4)" 
 

"" println


/* ASSIGNMENT:
 * Create a function that will allow creating new vectors in the following way:
 */
 
Vect with := method(nx, ny, // x, y,
		temp := Vect clone  // temp := Vect clone
		temp x = nx         // temp x = x
		temp y = ny			// temp y = y
		temp				// temp ... works as well
	)

Vect with (7, 9) println



"" println

/* ASSIGNMENT:
 * Create function length2 that will return a number, which however when printed out 
 * it returns a string like: "d(3,4) = 5"
 * 
 * d1 := v1 length2
 * d1 println // prints "d(3,4) = 5"
 * (d1 + 2) println // since d1 is a number, this prints out 7
 */
 
Vect length2 := method (
	res := length clone
	res asString := "d(#{x},#{y}) = #{length}" interpolate
	res
)

d1 := v1 length2
d1 println // prints "d(3,4) = 5"
(d1 + 2) println // since d1 is a number, this prints out 7