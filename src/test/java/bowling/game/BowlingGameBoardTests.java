package bowling.game;

import bowling.Pin;
import bowling.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("볼링 게임판 테스트")
public class BowlingGameBoardTests {

    BowlingGameBoard bowlingGameBoard = BowlingGameBoard.newInstance("AAA", "BBB");

    @DisplayName("생성 테스트")
    @Test
    public void generateTest() {
        assertThatCode(() -> BowlingGameBoard.newInstance("AAA", "BBB")).doesNotThrowAnyException();
    }

    @DisplayName("사용자 턴 테스트")
    @Test
    public void getNextTurnTest() {
        BowlingGame game = bowlingGameBoard.getNextTurn();
        assertThat(game.getPlayer()).isEqualTo(Player.of("AAA"));

        game.bowl(Pin.ofMax());

        BowlingGame anotherGame = bowlingGameBoard.getNextTurn();
        assertThat(anotherGame.getPlayer()).isEqualTo(Player.of("BBB"));
    }

    @DisplayName("사용자 턴 테스트2")
    @Test
    public void getNextTurnTest2() {
        BowlingGame game = bowlingGameBoard.getNextTurn();
        assertThat(game.getPlayer()).isEqualTo(Player.of("AAA"));

        game.bowl(Pin.of(7));

        BowlingGame anotherGame = bowlingGameBoard.getNextTurn();
        assertThat(anotherGame.getPlayer()).isEqualTo(Player.of("AAA"));
    }
}
