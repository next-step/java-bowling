package utils;

public class StringUtils {

    public static boolean isAllEnglishLetter(String string){
        return string.toLowerCase().chars()
                .mapToObj(StringUtils::isLowerCharRange)
                .reduce((x, y) -> x && y)
                .orElse(false);
    }

    private static boolean isLowerCharRange(int letter){
        return 97 <= letter && letter <= 122;
    }
}
