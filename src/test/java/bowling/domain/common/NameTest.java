package bowling.domain.common;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import bowling.domain.common.exception.NameCreateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("이름")
class NameTest {

    @DisplayName("[성공] 생성")
    @ParameterizedTest
    @CsvSource({
        "PJS", "pjs", "pJs",
        "CBH", "cbh", "CbH",
    })
    public void create(final String input) {
        // given

        // when
        final Name name = Name.of(input);

        // then
        assertThat(name).isNotNull();
    }

    @DisplayName("[실패] 생성 - 유효하지 않은 이름")
    @ParameterizedTest
    @CsvSource({
        "PJSS", "p", "PJ", "''"
    })
    public void create_invalidName(final String input) {
        // given

        // when
        assertThrows(NameCreateException.class, () -> Name.of(input));

        // then
    }
}
