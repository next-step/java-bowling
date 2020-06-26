package bowling.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("StringUtils 테스트")
class StringUtilsTest {

    private final int TEXT_SIZE = 6;

    @DisplayName("문자 길이와 가운데 정렬")
    @ParameterizedTest
    @CsvSource(delimiter =':', value = {"테스트:'  테스트 '","1프레임:' 1프레임 '","01:'  01  '"})
    void center(String input, String expect) {
        assertThat(StringUtils.center(input, TEXT_SIZE)).isEqualTo(expect);
    }
}
