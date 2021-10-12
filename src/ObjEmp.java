public class ObjEmp<T extends Number> {
    private final T value;

    public ObjEmp(final T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public ObjEmp<T> add(ObjEmp<T> popped) {
        switch (this.value.getClass().getSimpleName()) {
            case "Integer" -> {
                return new ObjEmp(this.value.intValue() + popped.value.intValue());
            }
            case "Double" -> {
                return new ObjEmp(this.value.doubleValue() + popped.value.doubleValue());
            }
            case "Float" -> {
                return new ObjEmp(this.value.floatValue() + popped.value.floatValue());
            }
            case "Short" -> {
                return new ObjEmp(this.value.shortValue() + popped.value.shortValue());
            }
            case "Byte" -> {
                return new ObjEmp(this.value.byteValue() + popped.value.byteValue());
            }
            case "Long" -> {
                return new ObjEmp(this.value.longValue() + popped.value.longValue());
            }
            default -> {
                return null;
            }
        }
    }
}
