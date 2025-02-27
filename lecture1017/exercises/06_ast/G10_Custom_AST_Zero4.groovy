import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target
import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.ClassHelper
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.Parameter
import org.codehaus.groovy.ast.builder.AstBuilder
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformationClass
import groovyjarjarasm.asm.Opcodes
import static org.codehaus.groovy.control.CompilePhase.SEMANTIC_ANALYSIS

@Retention(RetentionPolicy.SOURCE)
@Target([ElementType.TYPE])
@GroovyASTTransformationClass("ZeroTransformation4")
public @interface Zero4 {}

@GroovyASTTransformation(phase = SEMANTIC_ANALYSIS)
public class ZeroTransformation4 implements ASTTransformation {

    public void visit(ASTNode[] astNodes, SourceUnit source) {
        ClassNode annotatedClass = astNodes[1]
        
        AstBuilder ab = new AstBuilder()
        List<ASTNode> res = ab.buildFromSpec {
            method('getZero', Opcodes.ACC_PUBLIC, Integer) { //kancer goes here
                parameters{}
                exceptions{}
                block{
                    returnStatement{
                        constantExpression{0} //or constant(0)
                    }
                }
            }
        }
        
        annotatedClass.addMethod(res[0]) //a lot simpler, see line 33
    }
}

final calculator = new GroovyShell(Zero4.class.getClassLoader()).evaluate('''
@Zero2
class Calculator {}

new Calculator()
''')

assert 0 == calculator.zero

println 'done'
