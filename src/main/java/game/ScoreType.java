package game;

import java.util.Arrays;

public enum ScoreType {
    ZERO(0, "-")
    ,OME(1, "1")
    ,TWO(2, "2")
    ,THREE(3, "3")
    ,FOUR(4, "4")
    ,FIVE(5, "5")
    ,SIX(6, "6")
    ,SEVEN(7, "7")
    ,EIGHT(8, "8")
    ,NINE(9, "9")
    ,TEN(10, "X");

    int number;
    String numberString;
    ScoreType(int number, String numberString) {
        this.number = number;
        this.numberString = numberString;
    }

    public int getNumber() {
        return number;
    }

    public String getNumberString() {
        return numberString;
    }

    public static String toString(int number) {
        return Arrays.stream(values()).filter(scoreType -> scoreType.getNumber() == number)
                .findFirst()
                .map(scoreType -> scoreType.getNumberString())
                .orElseThrow(IllegalArgumentException::new);
    }

}
