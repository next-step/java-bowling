package bowling.utils;

public class StringUtils {

    public static boolean isAlphabet(String input) {
        return input.chars()
                .mapToObj(c -> (char) c)
                .filter(StringUtils::isAlphabet)
                .count() == input.length();
    }

    private static boolean isAlphabet(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }
}
