package stackable;

import stackable.exceptions.IncorrectOperation;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class Complex implements ObjEmp {
    private Double real;
    private Double im;

    public Complex(final Double real,
                   final Double im) {
        this.real = real;
        this.im = im;
    }

    public static Complex fromStr(String str) {
        List<String> splittedString = new LinkedList<>(Arrays.asList(str.split("")));
        splittedString.removeAll(Collections.singleton(" "));
        List<String> newList = new LinkedList<>();
        IntStream.range(0, splittedString.size()).forEachOrdered(i -> {
            switch (splittedString.get(i)) {
                case "+", "-"  -> newList.add(splittedString.get(i) + splittedString.get(i + 1));
                case String s && (s.equals("i") && i > 0) -> newList.set(newList.size() - 1, newList.get(newList.size() - 1) + "i");
                case String s && i > 0 && Arrays.asList("+", "-").contains(splittedString.get(i - 1)) -> {}
                default -> newList.add(splittedString.get(i));
            }
        });
        System.out.println(newList);
        System.out.println(Double.valueOf(newList.get(0)));
        if (newList.size() == 1) return new Complex(0.0, Double.valueOf(newList.get(0).substring(0, newList.get(0).length() - 1)));
        else if (newList.size() == 2) return new Complex(
                Double.valueOf(newList.get(0)),
                Double.valueOf(newList.get(1).substring(0, newList.get(1).length() - 1))
        );
        else return null;
    }

    @Override
    public String toString() {
        return (this.real != 0 ?
                this.real + (this.im > 0 ? " + " + this.im : " - " + Math.abs(this.im))  :
                this.im) + "i";
    }

    @Override
    public ObjEmp add(ObjEmp o) throws IncorrectOperation {
        if (o instanceof Number)
            return new Complex(this.real + ((Number) o).getValue(), this.im);
        else if (o instanceof Complex)
            return new Complex(this.real + ((Complex) o).real, this.im + ((Complex) o).im);
        throw new IncorrectOperation("");
    }

    @Override
    public ObjEmp sub(ObjEmp o) throws IncorrectOperation {
        if (o instanceof Number)
            return new Complex(this.real - ((Number) o).getValue(), this.im);
        else if (o instanceof Complex)
            return new Complex(this.real - ((Complex) o).real, this.im + ((Complex) o).im);
        throw new IncorrectOperation("");
    }

    @Override
    public ObjEmp mul(ObjEmp o) throws IncorrectOperation {
        if (o instanceof Number)
            return new Complex(this.real * ((Number) o).getValue(), this.im * ((Number) o).getValue());
        else if (o instanceof Complex)
            return new Complex(
                    this.real * ((Complex) o).real - this.im * ((Complex) o).im,
                    this.real * ((Complex) o).im + this.im * ((Complex) o).real
            );
        throw new IncorrectOperation("");
    }

    @Override
    public ObjEmp div(ObjEmp o) throws IncorrectOperation {
        if (o instanceof Number) {
            if (((Number) o).getValue() != 0)
                return new Complex(this.real / ((Number) o).getValue(), this.im / ((Number) o).getValue());
            throw new IncorrectOperation("");
        }
        else if (o instanceof Complex) {
            if (((Complex) o).real != 0 && ((Complex) o).im != 0) {
                return new Complex(
                        (this.real * ((Complex) o).real + this.im * ((Complex) o).im) /
                                (((Complex) o).real * ((Complex) o).real + ((Complex) o).im * ((Complex) o).im),
                        (this.im * ((Complex) o).real - this.real * ((Complex) o).im) /
                                (((Complex) o).real * ((Complex) o).real + ((Complex) o).im * ((Complex) o).im)
                );
            }
            throw new IncorrectOperation("");
        }
        throw new IncorrectOperation("");
    }

    @Override
    public ObjEmp mod(ObjEmp o) throws IncorrectOperation {
        throw new IncorrectOperation("");
    }
}
