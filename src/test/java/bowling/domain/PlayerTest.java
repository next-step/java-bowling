package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("플레이어 로직 테스트")
class PlayerTest {

    @DisplayName("플레이어 생성 성공")
    @ParameterizedTest
    @MethodSource("source_create_player_shouldSuccess")
    public void create_player_shouldSuccess(String playerName) {
        Player player = new Player(playerName);
        assertThat(player.getName()).isEqualTo(playerName);
    }

    public static Stream<Arguments> source_create_player_shouldSuccess() {
        return Stream.of(
                Arguments.of("jaekwon"),
                Arguments.of("jk")
        );
    }
}
