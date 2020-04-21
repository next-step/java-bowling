package bowling.game;

import bowling.Pin;
import bowling.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("볼링 게임 턴 테스트")
public class BowlingGameTurnTests {

    private BowlingGameTurn bowlingGameTurn = BowlingGameTurn.newInstance(Arrays.asList(BowlingGame.newInstance("AAA"), BowlingGame.newInstance("BBB")));

    @DisplayName("생성 턴 테스트")
    @Test
    public void generateBowlingGameInfoTest() {
        assertThatCode(() -> BowlingGameTurn.newInstance(Arrays.asList(BowlingGame.newInstance("AAA"), BowlingGame.newInstance("BBB"))));
    }

    @DisplayName("다음 턴 테스트")
    @Test
    public void getNextTurnTest() {
        BowlingGame nextTurnGame = bowlingGameTurn.getNextTurn();
        assertThat(nextTurnGame.getPlayer()).isEqualTo(Player.of("AAA"));
    }

    @DisplayName("다음 턴 (남은 턴이 없을 때) 테스트")
    @Test
    public void getNextTurnEmptyTest() {
        BowlingGame first = bowlingGameTurn.getNextTurn();
        first.bowl(Pin.ofMax());

        BowlingGame second = bowlingGameTurn.getNextTurn();
        second.bowl(Pin.ofMax());

        assertThat(bowlingGameTurn.getNextTurn()).isEqualTo(null);
    }
}
