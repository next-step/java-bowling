package bowling.game;

import bowling.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class BowlingGameTurnTests {

    private BowlingGameTurn bowlingGameTurn = BowlingGameTurn.newInstance(Arrays.asList(BowlingGame.newInstance("AAA"), BowlingGame.newInstance("BBB")));

    @DisplayName("생성 턴 테스트")
    @Test
    public void generateBowlingGameInfoTest() {
        assertThatCode(() -> BowlingGameTurn.newInstance(Arrays.asList(BowlingGame.newInstance("AAA"), BowlingGame.newInstance("BBB"))));
    }

    @DisplayName("사용자 턴 테스트")
    @Test
    public void getNextTurnTest() {
        assertThat(bowlingGameTurn.getNextTurn()).isEqualTo(BowlingGame.newInstance("AAA"));
    }

    @DisplayName("사용자 턴 (남은 턴이 없을 때) 테스트")
    @Test
    public void getNextTurnEmptyTest() {
        BowlingGame first = bowlingGameTurn.getNextTurn();
        first.bowl(Pin.ofMax());

        BowlingGame second = bowlingGameTurn.getNextTurn();
        second.bowl(Pin.ofMax());

        assertThat(bowlingGameTurn.getNextTurn()).isEqualTo(null);
    }
}
