package bowling.domain.player;

import bowling.exception.NameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameTest {
    @ParameterizedTest
    @DisplayName("이름이 3글자가 아닌 경우 NameException")
    @ValueSource(strings = {"hi", "hihi", "h", ""})
    void playerNameLengthTest(String name) {
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(NameException.class);
    }

    @ParameterizedTest
    @DisplayName("Player 이름이 영어가 아닐경우 NameException")
    @ValueSource(strings = {"가가가", "아아아", "가나다"})
    void playerNameNotEng(String name) {
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(NameException.class );
    }

    @ParameterizedTest
    @DisplayName("Name 생성 테스트")
    @MethodSource("createNameSource")
    void createName(Name name, String playerName) {
        assertThat(name).isEqualTo(new Name(playerName));
    }

    static Stream<Arguments> createNameSource() {
        return Stream.of(
                Arguments.of(new Name("ddd"), "DDD"),
                Arguments.of(new Name("tdd"), "tdd"),
                Arguments.of(new Name("ATD"), "atd")
        );
    }
}