package utils.numbers;

public final class NumHelper {
    public static boolean isNumeric(String str) {
        return str.matches("-?\\d*\\.?\\d+");
    }

    public static boolean isVector(String str) {
        return str.matches("^\\[((\\d*\\.?\\d+),?[ ]*?)+]$");
    }

    public static boolean isComplex(String str) {
        return str.matches("(-?\\d*\\.?\\d+)?i|((-?\\d*\\.?\\d+)[ ]*?[+-][ ]*?(-?\\d*\\.?\\d+)i)");
    }
}
