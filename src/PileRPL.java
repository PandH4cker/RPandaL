import java.util.Arrays;
import java.util.Stack;

public class PileRPL {
    private int size;
    private Stack<ObjEmp> objEmpStack;

    public PileRPL(final int size) {
        this.size = size;
        this.objEmpStack = new Stack<>();
    }

    @Override
    public String toString() {
        return Arrays.toString(objEmpStack.toArray());
    }

    public void stack(ObjEmp o) {
        this.objEmpStack.push(o);
    }

    public void add() {
        this.objEmpStack.push(this.objEmpStack.pop().add(this.objEmpStack.pop()));
        //this.objEmpStack.push(new ObjEmp(this.objEmpStack.pop().getValue() + this.objEmpStack.pop().getValue()));
    }
}
