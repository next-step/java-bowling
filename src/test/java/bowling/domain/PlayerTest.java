package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Player: 플레이어 로직 테스트")
class PlayerTest {

    @DisplayName("이름의 길이를 입력 받아 생성")
    @ParameterizedTest
    @MethodSource("source_getName_ReturnName")
    public void getName_ReturnName(String name) {
        Player player = new Player(name);
        assertThat(player.getName()).isEqualTo(name);
    }

    public static Stream<Arguments> source_getName_ReturnName() {
        return Stream.of(
                Arguments.of("PJS"),
                Arguments.of("JAE"));
    }

    @DisplayName("이름의 길이가 3자가 아니면 예외 발생")
    @ParameterizedTest
    @CsvSource(value = {"po", "honu", "crong", "jk"}, delimiter = ',')
    public void create_WithNameLengthNot3_ExceptionThrown(String name) {
        assertThatThrownBy(() -> {
            new Player(name);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
