package dto.util;

import java.util.List;

public class ListUtil {

    private static final String EMPTY = "";

    public static void addEmptyString(List<String> list, int amount) {
        for (int i = 0; i < amount; i++) {
            list.add(EMPTY);
        }
    }
}
