package bowling.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class StringUtilsTest {

  @Test
  void join() {
    List<String> strings = Arrays.asList("test", "sample", "end");
    assertEquals(StringUtils.join(strings, "|"), "test|sample|end");
  }

}