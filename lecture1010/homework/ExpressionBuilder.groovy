// 2022/2023
// TASK The MarkupBuilder in Groovy can transform a hierarchy of method calls and nested closures into a valid XML document.
// Create a NumericExpressionBuilder builder that will read a user-specified hierarchy of simple math expressions and build a tree representation of it.
// The basic arithmetics operations as well as the power (aka '^') operation must be supported.
// It will feature a toString() method that will pretty-print the expression tree into a string with the same semantics, as verified by the assert on the last line.
// This means that parentheses must be placed where necessary with respect to the mathematical operator priorities.
// Change or add to the code in the script. Reuse the infrastructure code at the bottom of the script.
class NumericExpressionBuilder extends BuilderSupport {
    @Override
    String toString() {
        root.toString()
    }

    protected Object checkRoot(Object toCheck) {
        if (!root)
            root = toCheck
        return toCheck
    }

    protected void setParent(Object parent, Object child) {
        parent.children << child
    }

    protected Object createNode(Object nodeName) {
        createNode nodeName, null, null
    }

    protected Object createNode(Object nodeName, Object value) {
        createNode nodeName, null, value
    }

    protected Object createNode(Object nodeName, Map attrs) {
        createNode nodeName, attrs, null
    }

    protected Object createNode(Object nodeName, Map attrs, Object value) {
        final node = new Item(name: nodeName)
        if (attrs) {
            node.attrs = attrs
        }
        return checkRoot(node)
    }

    Item root = null
}

class Item {
    static Map priority = ['+' : 10, '-' : 10, '*' : 20, '/' : 20, 'power' : 30]
    static Map symbols = ['+' : '+', '-' : '-', '*' : '*', '/' : '/', 'power' : '^']

    String name
    Map attrs
    final List children = []

    @Override
    String toString() {
        toStringPriority(Integer.MIN_VALUE)
    }

    String toStringPriority(int prevPriority) {
        def expression = new StringBuilder()

        if (priority[name] < prevPriority) {
            expression << '('
        }

        if (children.size() > 0) {
            expression << children[0].toStringPriority(priority[name]) << ' '
            expression << symbols[name] << ' '
            expression << children[1].toStringPriority(priority[name])
        }
        else {
            return attrs['value']
        }

        if (priority[name] < prevPriority) {
            expression << ')'
        }

        return expression.toString()
    }
}





//------------------------- Do not modify beyond this point!

def build(builder, String specification) {
    def binding = new Binding()
    binding['builder'] = builder
    new GroovyShell(binding).evaluate(specification)
    return builder
}

//Custom expression to display. It should be eventually pretty-printed as 10 + x * (2 - 3) / 8 ^ (9 - 5)
String description = '''
builder.'+' {
    number(value: 10)
    '*' {
        variable(value: 'x')
        '/' {
            '-' {
                number(value: 2)
                number(value: 3)
            }
            power {
                number(value: 8)
                '-' {
                    number(value: 9)
                    number(value: 5)
                }
            }
        }
    }
}
'''

//XML builder building an XML document
def xml = build(new groovy.xml.MarkupBuilder(), description)
println xml.toString()

//NumericExpressionBuilder displaying the expression
def expression = build(new NumericExpressionBuilder(), description)
println (expression.toString())
assert '10 + x * (2 - 3) / 8 ^ (9 - 5)' == expression.toString()