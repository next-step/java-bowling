package bowling.utils;

import java.util.List;

public final class StringUtils {

  public static String join(List<String> strings, String separator) {
    return strings.stream().reduce((a, b) -> a + separator + b).orElse("");
  }

}
