package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertAll;

class PlayersTest {
    public static final Players ONE_PLAYER = Players.from(Collections.singletonList(PlayerTest.PLAYER_AYM));
    public static final Players TWO_PLAYERS = Players.from(Arrays.asList(PlayerTest.PLAYER_AYM, PlayerTest.PLAYER_KMS));

    private static Stream<Players> providePlayersCases() { // argument source method
        return Stream.of(ONE_PLAYER, TWO_PLAYERS);
    }

    @DisplayName("플레이어들 생성 확인")
    @ParameterizedTest
    @MethodSource("providePlayersCases")
    void from(Players players) {
        // then
        assertThat(players).isNotNull();
    }

    @DisplayName("플레이어들 생성 예외 확인")
    @ParameterizedTest
    @NullAndEmptySource
    void fromFailed(List<Player> players) {
        // when & then
        assertThatIllegalArgumentException().isThrownBy(
                () -> Players.from(players)
        );
    }

    @DisplayName("플레이어들 반환 확인")
    @Test
    void getPlayers() {
        // when & then
        assertAll(
                () -> assertThat(ONE_PLAYER.getPlayers()).isEqualTo(Collections.singletonList(PlayerTest.PLAYER_AYM)),
                () -> assertThat(TWO_PLAYERS.getPlayers()).isEqualTo(Arrays.asList(PlayerTest.PLAYER_AYM, PlayerTest.PLAYER_KMS))
        );
    }
}
