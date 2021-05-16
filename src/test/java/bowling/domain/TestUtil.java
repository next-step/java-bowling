package bowling.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestUtil {
    public static List<Integer> stringListToIntegerList(String string, String delimeter){
        return Arrays.stream(string.split(delimeter))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
