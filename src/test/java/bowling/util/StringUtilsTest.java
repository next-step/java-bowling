package bowling.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class StringUtilsTest {
    @ParameterizedTest
    @CsvSource(value = { "abc:5:. abc .", "abc:6:.  abc .", "hello:8:.  hello .", "5|5:5:. 5|5 ." }, delimiter = ':')
    public void center(String text, int length, String excepted) {
        assertThat(StringUtils.center(text, length)).isEqualTo(excepted.replaceAll("\\.", ""));
    }
}
