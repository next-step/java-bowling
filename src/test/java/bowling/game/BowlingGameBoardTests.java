package bowling.game;

import bowling.Pin;
import bowling.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

public class BowlingGameBoardTests {

    @DisplayName("생성 테스트")
    @Test
    public void generateTest() {
        assertThatCode(() -> BowlingGameBoard.newInstance("AAA", "BBB")).doesNotThrowAnyException();
    }

    @DisplayName("사용자 투구 테스트")
    @Test
    public void getNextTurnTest() {
        assertThatCode(() -> BowlingGameBoard.bowl(Player.of("AAA"), Pin.ofMax())).doesNotThrowAnyException();
    }

    @DisplayName("사용자 턴 테스트")
    @Test
    public void getNextTurnTest() {
        assertThat(BowlingGameBoard.getNextPlayer()).isEqualTo(Player.of("AAA"));
        BowlingGameBoard.bowl(Player.of("AAA"), Pin.ofMax());
        assertThat(BowlingGameBoard.getNextPlayer()).isEqualTo(Player.of("BBB"));
    }

    @DisplayName("사용자 턴 테스트2")
    @Test
    public void getNextTurnTest2() {
        assertThat(BowlingGameBoard.getNextTurn()).isEqualTo(Player.of("AAA"));
        BowlingGameBoard.bowl(Player.of("AAA"), Pin.of(7));
        assertThat(BowlingGameBoard.getNextTurn()).isEqualTo(Player.of("AAA"));
    }
}
