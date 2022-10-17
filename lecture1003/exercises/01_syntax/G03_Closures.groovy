Closure multiply = {a, b -> a * b}

assert "MFF" == "M" + multiply("F", 2)

assert 6 == multiply(2, 3)

//TASK Make increment to use the implicit parameter
Closure increment = {
    if (it < 100) it + 1
    else it
}

assert 11 == increment(10)
assert 100 == increment(100)

println 'ok'