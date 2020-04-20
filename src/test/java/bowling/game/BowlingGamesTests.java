package bowling.game;

import bowling.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

@DisplayName("다수 볼링 게임 테스트")
public class BowlingGamesTests {

    private BowlingGames bowlingGames = BowlingGames.newInstance("AAA", "BBB");

    @DisplayName("생성 테스트")
    @Test
    public void generateTest() {
        assertThatCode(() -> BowlingGames.newInstance("AAA", "BBB")).doesNotThrowAnyException();
    }

    @DisplayName("다음 프레임 추가 테스트")
    @Test
    public void prePareNextFramesTest() {
        BowlingGameTurn gameTurn = bowlingGames.makeNewGameTurn();

        BowlingGame game1 = gameTurn.getNextTurn();
        game1.bowl(Pin.ofMax());

        BowlingGame game2 = gameTurn.getNextTurn();
        game2.bowl(Pin.of(5));
        game2.bowl(Pin.of(2));

        assertThatCode(() -> bowlingGames.prepareNextFrames()).doesNotThrowAnyException();
    }

    @DisplayName("다음 프레임 추가 테스트 - 에러")
    @Test
    public void prePareNextFramesTest2() {
        BowlingGameTurn gameTurn = bowlingGames.makeNewGameTurn();

        BowlingGame game1 = gameTurn.getNextTurn();
        game1.bowl(Pin.ofMax());
        assertThatIllegalStateException()
                .isThrownBy(() -> bowlingGames.prepareNextFrames());
    }
}
