package utils;

public class StringUtils {

    public static boolean isAllEnglishLetter(String string){
        return string.toLowerCase().chars()
                .mapToObj(StringUtils::isLowerCharRange)
                .reduce((x, y) -> x && y)
                .orElse(false);
    }

    public static String center(String text, int len){
        String out = String.format("%"+len+"s%s%"+len+"s", "",text,"");
        int mid = (out.length()/2);
        int start = mid - (len/2);
        int end = start + len;
        return out.substring(start, end);
    }

    private static boolean isLowerCharRange(int letter){
        return 97 <= letter && letter <= 122;
    }
}
