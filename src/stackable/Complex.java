package stackable;

import stackable.exceptions.IncorrectOperation;

import java.util.Arrays;

public class Complex implements ObjEmp {
    private Double real;
    private Double im;

    public Complex(final Double real,
                   final Double im) {
        this.real = real;
        this.im = im;
    }

    public static Complex fromStr(String str) {
        String[] splittedString = str.split("");
        System.out.println(Arrays.toString(splittedString));
        return new Complex(5.0, 5.0);
    }

    @Override
    public String toString() {
        return this.real + " " + (this.im > 0 ? "+ " + this.im : this.im);
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
