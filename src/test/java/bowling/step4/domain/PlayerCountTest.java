package bowling.step4.domain;

import bowling.step4.domain.frame.NormalFrame;
import bowling.step4.exception.PlayerMinimumCountException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerCountTest {

    @DisplayName("플레이어의 수가 1 미만일 경우 PlayerMinimumCountException 발생")
    @ParameterizedTest
    @ValueSource(ints = { -1, 0 })
    void 플레이어_수_검증(int playerCount) {
        assertThatExceptionOfType(PlayerMinimumCountException.class)
            .isThrownBy(() -> PlayerCount.of(playerCount));
    }

    @DisplayName("플레이어 수 만큼의 PlayersFrames를 생성하는지 확인")
    @ParameterizedTest
    @MethodSource("providePlayersFramesByPlayerCount")
    void PlayersFrames_생성_테스트(int playerCount, Function<Integer, PlayerFrames> initializer, String expected) {
        PlayersFrames playersFrames = PlayerCount.of(playerCount).ofPlayersFrames(initializer);
        assertEquals(
            expected,
            playersFrames.stream()
                         .map(PlayerFrames::getPlayerName)
                         .collect(joining(";"))
        );
    }

    private static Stream<Arguments> providePlayersFramesByPlayerCount() {
        Function<Integer, PlayerFrames> initializer = i -> {
            Player player = Player.valueOf("p" + i);
            return PlayerFrames.of(player, NormalFrame.start());
        };
        return Stream.of(
            Arguments.of(2, initializer, "p1;p2"),
            Arguments.of(3, initializer, "p1;p2;p3"),
            Arguments.of(4, initializer, "p1;p2;p3;p4")
        );
    }
}