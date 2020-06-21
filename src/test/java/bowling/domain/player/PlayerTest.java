package bowling.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {

    private Player createPlayer(String name) {
        return new Player(name);
    }

    @Test
    @DisplayName("이름이 null인 경우 Exception")
    void nullTest() {
        assertThatThrownBy(() -> this.createPlayer(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이름이 비어있는 경우 Exception")
    void emptyTest() {
        assertThatThrownBy(() -> this.createPlayer(""))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이름이 3자 초과인 경우 Exception")
    void validMaxLength() {
        assertThatThrownBy(() -> this.createPlayer("ABCD"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("provideNames")
    @DisplayName("정상 생성")
    void createTest(String name) {
        Player player = this.createPlayer(name);
        assertThat(player.getName()).isEqualTo(name);
    }

    private static final Stream<Arguments> provideNames() {
        return Stream.of(
                Arguments.of("A"),
                Arguments.of("AB"),
                Arguments.of("ABC")
        );
    }
}