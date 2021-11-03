package stackable;

import stackable.exceptions.IncorrectOperation;

public class Number implements ObjEmp {
    private Double value;

    public Number(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    @Override
    public ObjEmp add(ObjEmp o) throws IncorrectOperation {
        if (o instanceof Number)
            return new Number(this.value + ((Number) o).getValue());
        if (o instanceof Complex)
            return o.add(this);
        throw new IncorrectOperation("");
    }

    @Override
    public ObjEmp sub(ObjEmp o) throws IncorrectOperation {
        if (o instanceof Number)
            return new Number(this.value - ((Number) o).getValue());
        if (o instanceof Complex)
            return o.sub(this);
        throw new IncorrectOperation("");
    }

    @Override
    public ObjEmp mul(ObjEmp o) throws IncorrectOperation {
        if (o instanceof Number)
            return new Number(this.value * ((Number) o).getValue());
        else if (o instanceof Vector)
            return o.mul(this);
        else if (o instanceof Complex)
            return o.mul(this);
        throw new IncorrectOperation("");
    }

    @Override
    public ObjEmp div(ObjEmp o) throws IncorrectOperation {
        if (o instanceof Number) {
            if (((Number) o).getValue() != 0)
                return new Number(this.value / ((Number) o).getValue());
            throw new IncorrectOperation("");
        } else if (o instanceof Vector)
            return o.div(this);
        else if (o instanceof Complex)
            return new Complex(this.value, 0.0).div(o);
        throw new IncorrectOperation("");
    }

    @Override
    public ObjEmp mod(ObjEmp o) throws IncorrectOperation {
        if (o instanceof Number) {
            if (((Number) o).getValue() != 0)
                return new Number(this.value % ((Number) o).value);
            throw new IncorrectOperation("");
        }
        throw new IncorrectOperation("");
    }
}
