String.metaClass.backToFront = {->
    delegate[-1..0]
}

String.metaClass.starTrim = {->
    "*" + delegate.trim() + "*"
}

println 'cimanyd si yvoorG'.backToFront()



//TASK define a starTrim() method to surround the original trimmed string with '*' 

assert '*core*' == '   core   '.starTrim()

println 'done'


















