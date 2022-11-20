/*
Implement the classes below such that the main (without modifications) prints out the something like this:

Person John Doe aged 24
Person John Doe aged 25
List(h2.PersonState@3d24753a)
Person John Doe aged 24
Thing Box with color (255,0,0)
Person Joe aged 24

*/

package h2

import scala.collection.mutable.ListBuffer

trait WithExplicitState:
  type EState

  protected var actualState : EState

  protected def state: EState

  protected def state_=(state: EState): Unit


class PersonState(val name: String, val age: Int)

class Person extends WithExplicitState:
  type EState = PersonState

  var actualState : PersonState = new PersonState("", -1)

  override def state: PersonState = actualState

  override def state_=(state: PersonState): Unit =
    actualState = state

  def setName(value: String) : this.type =
    state = new PersonState(value, state.age)
    this

  def setAge(value: Int) : this.type =
    state = new PersonState(state.name, value)
    this

  override def toString() : String =
    s"Person ${state.name} is ${state.age} years old."

type RGBColor = (Int, Int , Int)
class ThingState(val name: String, val color: RGBColor)

class Thing extends WithExplicitState:
  type EState = ThingState

  var actualState : ThingState = new ThingState("", (0, 0, 0))

  override def state: ThingState = actualState

  override def state_=(state: ThingState): Unit = 
    actualState = state

  def setName(value: String) : this.type =
    state = new ThingState(value, state.color)
    this

  def setColor(value: RGBColor) : this.type = 
    state = new ThingState(state.name, value)
    this

  override def toString() : String =
    s"Thing named \"${state.name}\" has RGB encoded color: ${state.color}."

trait History extends WithExplicitState:
    val hist = ListBuffer.empty[EState]

    def checkpoint(): this.type =
      hist.append(state)
      this

    def history = hist.toList

    def restoreTo(s: EState): this.type =
      state = s
      this


object ExplicitStateTest:
  def main(args: Array[String]): Unit =
    val john = (new Person with History).setName("John Doe").setAge(24).checkpoint()

    println(john)
    john.setAge(25)

    println(john)
    println(john.history)

    val johnsPrevState = john.history(0)
    john.restoreTo(johnsPrevState)
    println(john)

    val box = new Thing with History
    box.setName("Box")
    box.setColor((255, 0, 0))
    println(box)

    val joe = new Person with History
    joe.restoreTo(johnsPrevState).setName("Joe")
    println(joe)

    //The line below must not compile. It should complain about an incompatible type.
    //box.restoreTo(johnsPrevState) //errors out, as it should:
    // [info] compiling 1 Scala source to /home/michal/mff/NPRG014/lecture-scala/exercises-homework/target/scala-3.0.0/classes ...
    // [error] -- [E007] Type Mismatch Error: /home/michal/mff/NPRG014/lecture-scala/exercises-homework/src/main/scala/h2/ExplicitStateTest.scala:125:18 
    // [error] 125 |    box.restoreTo(johnsPrevState) //errors out, as it should:
    // [error]     |                  ^^^^^^^^^^^^^^
    // [error]     |Found:    (johnsPrevState : john.EState)
    // [error]     |Required: box.EState²
    // [error]     |
    // [error]     |where:    EState  is a type in class Person which is an alias of h2.PersonState
    // [error]     |          EState² is a type in class Thing which is an alias of h2.ThingState
    // [error] one error found
    // [error] one error found
    // [error] (Compile / compileIncremental) Compilation failed
    // [error] Total time: 1 s, completed Nov 20, 2022, 4:15:24 PM
