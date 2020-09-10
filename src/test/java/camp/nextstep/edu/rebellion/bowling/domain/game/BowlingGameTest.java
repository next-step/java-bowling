package camp.nextstep.edu.rebellion.bowling.domain.game;

import camp.nextstep.edu.rebellion.bowling.domain.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BowlingGameTest {
    @DisplayName("볼링 게임이 잘 실행 되는지 확인")
    @Test
    public void bowlingGameTest() {
        // given
        Player player = new Player("ABC");

        // when
        BowlingGame bowlingGame = BowlingGame.start(player);

       // then
       assertAll(
               () -> assertThat(bowlingGame.hasNext()).isTrue(),
               () -> assertThat(bowlingGame.meetPassTurn()).isFalse()
       );
    }

    @DisplayName("투구 (프레임)가 종료 되었을 경우 다음 투구자에게 턴을 넘길 수 있는지 확인")
    @Test
    public void meetPassTurn() {
        // given
        Player player = new Player("ABC");

        // when
        BowlingGame bowlingGame = BowlingGame.start(player);
        bowlingGame.record(10); // STRIKE
        bowlingGame.record(1); // -| MISS
        bowlingGame.record(2); // -|

        // then
        assertAll(
                () -> assertThat(bowlingGame.hasNext()).isTrue(),
                () -> assertThat(bowlingGame.meetPassTurn()).isTrue()
        );
    }

    @DisplayName("투구 (프레임)가 종료 되었을 경우 다음 투구자에게 턴을 넘길 수 없는 확인")
    @Test
    public void meetNotPassTurn() {
        // given
        Player player = new Player("ABC");

        // when
        BowlingGame bowlingGame = BowlingGame.start(player);
        bowlingGame.record(1);

        // then
        assertAll(
                () -> assertThat(bowlingGame.hasNext()).isTrue(),
                () -> assertThat(bowlingGame.meetPassTurn()).isFalse()
        );
    }
}
