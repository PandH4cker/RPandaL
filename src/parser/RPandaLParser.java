package parser;

import stack.PileRPL;
import stackable.Complex;
import stackable.Number;
import stackable.Vector;
import utils.numbers.NumHelper;

import java.util.Scanner;

public class RPandaLParser {
    private Scanner in;
    private PileRPL stack;

    public RPandaLParser() {
        this.in = new Scanner(System.in);
        this.stack = new PileRPL();

        String input;
        while (!(input = in.nextLine()).equals("q")) {
            switch (input) {
                case "+" -> this.stack.add();
                case "-" -> this.stack.sub();
                case "*" -> this.stack.mul();
                case "/" -> this.stack.div();
                case "%" -> this.stack.mod();
                case "C", "c", "CE", "ce" -> this.stack.clear();
            }
            if (NumHelper.isNumeric(input)) this.stack.stack(new Number(Double.valueOf(input)));
            if (NumHelper.isVector(input)) this.stack.stack(Vector.fromStr(input));
            if (NumHelper.isComplex(input)) {
                System.out.println(Complex.fromStr(input));
            }
            System.out.println(this.stack);
        }
    }
}
