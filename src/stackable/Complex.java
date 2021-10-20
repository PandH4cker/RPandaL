package stackable;

import stackable.exceptions.IncorrectOperation;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
        Pattern firstPattern = Pattern.compile("[-+]?(\\d*\\.?\\d+)?i");
        Pattern secondPattern = Pattern.compile("([+-]?\\d*\\.?\\d+)[ ]*?([+-])[ ]*?((\\d*\\.?\\d+)?i)");
        Matcher firstMatcher = firstPattern.matcher(str);
        Matcher secondMatcher = secondPattern.matcher(str);
        if(secondMatcher.find()) {
            if (secondMatcher.group(3).equals("i")) return new Complex(
                    Double.valueOf(secondMatcher.group(1)),
                    Double.valueOf(secondMatcher.group(2) + secondMatcher.group(3).replace("i", "1"))
            );
            return new Complex(
                    Double.valueOf(secondMatcher.group(1)),
                    Double.valueOf(secondMatcher.group(2) + secondMatcher.group(3).replace("i", ""))
            );
        }
        else if (firstMatcher.find()) {
            if (firstMatcher.group(0).length() == 1)
                return new Complex(0.0, 1.0);
            else if (firstMatcher.group(0).length() == 2) {
                switch(firstMatcher.group(0).charAt(0)) {
                    case '+' -> {
                        return new Complex(0.0, 1.0);
                    }
                    case '-' -> {
                        return new Complex(0.0, -1.0);
                    }
                }
                return new Complex(0.0, Double.parseDouble(String.valueOf(firstMatcher.group(0).charAt(0))));
            }
            else if (firstMatcher.group(0).length() >= 3)
                return new Complex(0.0, Double.parseDouble(firstMatcher.group(0).substring(0, firstMatcher.group(0).length() - 1)));
        }
        return null;
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
