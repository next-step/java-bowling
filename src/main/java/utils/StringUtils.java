package utils;

public class StringUtils {

    public static final String VERTICAL_BAR = "|";
    public static final String GUTTER = "-";
    public static final String STRIKE = "X";
    public static final String SPARE = "/";

    public static String makeDoubleDigit(int i) {
        return String.format("%02d",i);
    }

    public static String makeSpareStr(String spareStr) {
        if(spareStr.length() > 0){
            return spareStr.substring(0, spareStr.length()-1) + SPARE;
        }
        return spareStr;
    }

    public static String makeFinalSpare(String spareStr) {
        if(spareStr.length() > 2){
            return spareStr.substring(0,2)+SPARE+spareStr.substring(3);
        }
        return spareStr;
    }
}
