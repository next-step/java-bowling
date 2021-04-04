package bowling.util;

public class StringUtils {

    private static final char DEFAULT_PAD = ' ';

    public static String center(String string, int size) {
        return center(string, size, DEFAULT_PAD);
    }

    public static String center(String string, int size, char pad) {
        if (string == null || size <= string.length())
            return string;

        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < (size - string.length()) / 2; i++) {
            sb.append(pad);
        }
        sb.append(string);
        while (sb.length() < size) {
            sb.append(pad);
        }
        return sb.toString();
    }

    public static boolean isAllAlphabet(String string) {
        return string.chars()
                .mapToObj(number -> (char)number)
                .allMatch(StringUtils::isAlphabet);
    }

    private static boolean isAlphabet(Character character){
        return (character >= 'a' && character <= 'z') || (character >= 'A' && character <= 'Z');
    }
}
