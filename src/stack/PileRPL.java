package stack;

import stackable.exceptions.IncorrectOperation;
import stackable.ObjEmp;

import java.util.EmptyStackException;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PileRPL {
    private Stack<ObjEmp> objEmpStack;

    public PileRPL() {
        this.objEmpStack = new Stack<>();
    }

    @Override
    public String toString() {
        return IntStream
                .iterate(this.objEmpStack.size() - 1, i -> i >= 0, i -> i - 1)
                .mapToObj(i -> i + ": " + this.objEmpStack.get(i) + "\n")
                .collect(Collectors.joining());
    }

    public void stack(ObjEmp o) {
        this.objEmpStack.push(o);
    }

    public void clear() {
        this.objEmpStack.removeAllElements();
    }

    public void add() {
        try {
            this.objEmpStack.push(this.objEmpStack.pop().add(this.objEmpStack.pop()));
        } catch (IncorrectOperation | EmptyStackException ignored) {}
    }

    public void mul() {
        try {
            this.objEmpStack.push(this.objEmpStack.pop().mul(this.objEmpStack.pop()));
        } catch (IncorrectOperation | EmptyStackException ignored) {}
    }

    public void div() {
        try {
            this.objEmpStack.push(this.objEmpStack.pop().div(this.objEmpStack.pop()));
        } catch (IncorrectOperation | EmptyStackException ignored) {}
    }

    public void sub() {
        try {
            this.objEmpStack.push(this.objEmpStack.pop().sub(this.objEmpStack.pop()));
        } catch (IncorrectOperation | EmptyStackException ignored) {}
    }

    public void mod() {
        try {
            this.objEmpStack.push(this.objEmpStack.pop().mod(this.objEmpStack.pop()));
        } catch (IncorrectOperation | EmptyStackException ignored) {}
    }
}
