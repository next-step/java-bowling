package camp.nextstep.edu.rebellion.bowling.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtil {
    private static final String DEFAULT_SPLIT_DELIMITER = ",";
    public static final String EMPTY = "";
    public static final String FORMAT = "| %3s\t";
    public static final String PIPE = "|";
    public static final String NAME = "NAME";
    public static final String ENTER = "\n";

    private StringUtil(){}

    public static List<String> convertList(String str) {
        return convertList(str, DEFAULT_SPLIT_DELIMITER);
    }

    public static List<String> convertList(String str, String delimiter) {
        if (isEmpty(str)) {
            return new ArrayList<>();
        }
        return Arrays.asList(str.trim().split(delimiter));
    }

    public static boolean isEmpty(String str) {
        return null == str || EMPTY.equals(str);
    }

    public static String parseInt(int number) {
        String appender = EMPTY;

        if (number < 10) {
            appender = "0";
        }

        return appender + number;
    }
}
