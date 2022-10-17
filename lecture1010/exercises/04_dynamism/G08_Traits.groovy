trait Flying {
    void fly() {println "I am flying!"}
    void foo() {println "fly foo"}
}

trait Quacking {
    void quack() {println "Quack!"}
    void foo() {println "quack foo"}
}

class Duck implements Flying, Quacking {}

def duck = new Duck()
duck.fly()
duck.quack()
duck.foo()



//TASK uncomment, see and explain
trait Aging {
    int age = 0
    void birthday() {age+=1}
}

duck = duck as Aging
10.times {
    duck.birthday()
}

println "Duck's age: ${duck.age}"
duck.quack()


//TASK uncomment, see and explain
trait ExtraQuacking extends Quacking {
    void quack() {println ((1..3).collect {"Quack!"})}
}

//def myDuck = new Duck().withTraits ExtraQuacking
def myDuck = new Duck()
myDuck = myDuck as ExtraQuacking
myDuck.quack()

//NOTE A proper class implementing the trait could have been used instead
 