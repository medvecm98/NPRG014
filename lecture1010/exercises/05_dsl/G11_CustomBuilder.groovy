class MyIndentingBuilder {

    def indent = 1

    def invokeMethod(String methodName, args) {
        def result = '';
        println '(0) Method name: ' + methodName + ', arg size: ' + args.size()
        if (args.size() > 0) {
            Closure closure = args[0]
            closure.delegate = this
            indent += 3
            result = closure() //this calls apparentely
            indent -= 3
        }
        println '(1) Method name: ' + methodName + ', arg size: ' + args.size()
        return "<$methodName>\n${' ' * indent}$result\n${' ' * (indent - 1)}</$methodName>"
    }
}

//TASK manipulate the value in "indent" so as the generated xml is nicely indented

def doc = new MyIndentingBuilder().html {
    body {
        div {
            "content"
        }
        bruh {
            "moment"
        }
    }
}

println doc