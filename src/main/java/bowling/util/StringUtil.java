package bowling.util;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {

    public static int length(CharSequence cs) {
        return cs == null ? 0 : cs.length();
    }

    public static boolean equals(CharSequence cs1, CharSequence cs2) {
        return cs1 == null ? cs2 == null : cs1.equals(cs2);
    }

    public static String center(String str, int size) {
        return StringUtils.center(str, size);
    }
}
