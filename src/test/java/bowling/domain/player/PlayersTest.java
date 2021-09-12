package bowling.domain.player;

import static bowling.domain.Fixture.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {
    @DisplayName("init메서드에 이름 문자열 데이터를 입력 받으면, Players객체를 생성한다")
    @Test
    void createTest() {
        assertThat(Players.init(Arrays.asList("KCS", "KJY"))).isInstanceOf(Players.class);
    }

    @DisplayName("init메서드에 null데이터를 입력 받으면, 예외를 던진다")
    @Test
    void exceptionTest() {
        assertThatThrownBy(() -> Players.init(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("게임이 종료되는 상황인 데이터를 입력 받으면, isGameFinish함수는 참을 반환한다")
    @Test
    void isGameFinishTest() {
        Players players = Players.init(Arrays.asList("KCS", "KJY"));
        players.players().get(0).frames().throwBalls(END_GAME_STATE_EXAMPLE1);
        players.players().get(1).frames().throwBalls(END_GAME_STATE_EXAMPLE2);
        assertThat(players.isGameFinish()).isTrue();
    }

    @DisplayName("게임이 종료되는 상황인 데이터를 입력 받으면, isGameFinish함수는 참을 반환한다")
    @Test
    void numberOfPlayersTest() {
        List<String> playerNames = Arrays.asList("KCS", "KJY");
        assertThat(Players.init(playerNames).numberOfPlayers()).isEqualTo(playerNames.size());
    }
}
