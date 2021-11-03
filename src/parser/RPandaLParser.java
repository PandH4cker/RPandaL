package parser;

import stack.PileRPL;
import stackable.Complex;
import stackable.Number;
import stackable.Vector;
import utils.numbers.NumHelper;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class RPandaLParser implements Runnable {
    private Socket socket;
    private Scanner in;
    private PrintWriter out;
    private PileRPL stack;

    public RPandaLParser(Socket socket) {
        this.socket = socket;
        this.stack = new PileRPL();
    }

    @Override
    public void run() {
        try {
            this.in = new Scanner(this.socket.getInputStream());
            this.out = new PrintWriter(this.socket.getOutputStream(), true);

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
                if (NumHelper.isComplex(input)) this.stack.stack(Complex.fromStr(input));
                this.out.println(this.stack);
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
