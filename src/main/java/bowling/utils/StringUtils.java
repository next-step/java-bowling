package bowling.utils;

import ch.qos.logback.core.pattern.SpacePadder;

public class StringUtils {
    public static final String EMPTY = "";

    public static String center(String inputString, int size) {
        int inputStringSize = inputString.length();
        int rightPad = (size - inputStringSize) / 2;

        StringBuilder sb = new StringBuilder();
        SpacePadder.rightPad(sb, inputString, inputStringSize+rightPad);
        inputString = sb.toString();
        sb = new StringBuilder();
        SpacePadder.leftPad(sb, inputString, size);
        return sb.toString();
    }
}
