package stackable;

import stackable.exceptions.IncorrectOperation;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Vector implements ObjEmp{
    private Double[] values;

    public Vector(final Double[] values) {
        this.values = values;
    }


    public static Vector fromStr(String str) {
        str = str.substring(1);
        str = str.substring(0, str.length() - 1);
        String[] splittedStr = str.split(",");
        IntStream.range(0, splittedStr.length).forEachOrdered(i -> splittedStr[i] = splittedStr[i].trim());
        return new Vector(Arrays.stream(splittedStr).map(Double::valueOf).toArray(Double[]::new));
    }

    @Override
    public String toString() {
        return Arrays.toString(values);
    }

    public Double[] getValues() {
        return values;
    }

    @Override
    public ObjEmp add(ObjEmp o) throws IncorrectOperation {
        if (o instanceof Vector) {
            IntStream.range(0, values.length).forEach(i -> this.values[i] += ((Vector) o).values[i]);
            return this;
        }
        throw new IncorrectOperation("");
    }

    @Override
    public ObjEmp sub(ObjEmp o) throws IncorrectOperation {
        if (o instanceof Vector) {
            IntStream.range(0, values.length).forEach(i -> this.values[i] -= ((Vector) o).values[i]);
            return this;
        }
        throw new IncorrectOperation("");
    }

    @Override
    public ObjEmp mul(ObjEmp o) throws IncorrectOperation {
        if (o instanceof Vector) {
            Double value = IntStream.range(0, values.length).mapToDouble(i -> this.values[i] * ((Vector) o).values[i]).sum();
            return new Number(value);
        } else if (o instanceof Number) {
            IntStream.range(0, values.length).forEachOrdered(i -> this.values[i] *= ((Number) o).getValue());
            return this;
        }
        throw new IncorrectOperation("");
    }

    @Override
    public ObjEmp div(ObjEmp o) throws IncorrectOperation {
        if (o instanceof Number) {
            if (((Number) o).getValue() != 0) {
                IntStream.range(0, values.length).forEachOrdered(i -> this.values[i] /= ((Number) o).getValue());
                return this;
            }
            throw new IncorrectOperation("");
        }
        throw new IncorrectOperation("");
    }

    @Override
    public ObjEmp mod(ObjEmp o) throws IncorrectOperation {
        if (o instanceof Number) {
            if (((Number) o).getValue() != 0) {
                IntStream.range(0, values.length).forEachOrdered(i -> this.values[i] %= ((Number) o).getValue());
                return this;
            }
            throw new IncorrectOperation("");
        }
        throw new IncorrectOperation("");
    }
}
