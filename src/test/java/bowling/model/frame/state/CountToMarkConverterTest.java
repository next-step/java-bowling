package bowling.model.frame.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("카운트 마크 컨버터")
class CountToMarkConverterTest {

    @ParameterizedTest
    @DisplayName("마크로 변경")
    @CsvSource({"0,-", "5,5", "10,x"})
    void convert(int count, String expected) {
        assertThat(CountToMarkConverter.convert(count)).isEqualTo(expected);
    }
}
