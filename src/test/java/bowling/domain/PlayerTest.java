package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @ParameterizedTest
    @DisplayName("Player 생성 테스트")
    @MethodSource("createPlayerSource")
    void createPlayer(Name name) {
        assertThat(name).isEqualTo(name);
    }

    static Stream<Arguments> createPlayerSource() {
        return Stream.of(
                Arguments.of(new Name("ddd")),
                Arguments.of(new Name("tdd")),
                Arguments.of(new Name("ATD"))
        );
    }
}