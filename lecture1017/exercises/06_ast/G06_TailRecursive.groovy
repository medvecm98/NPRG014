@groovy.transform.TailRecursive
def f(BigDecimal n, BigDecimal acc) {
    if (n < 2) acc
    else f(n - 1, acc * n)
}

def fact(BigDecimal n) {
    f(n, 1)
}

println fact(5)

//TASK Make the function tail recursive so that it can pass the following line
println fact(10000)