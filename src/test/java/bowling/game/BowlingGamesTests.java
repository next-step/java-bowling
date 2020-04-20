package bowling.game;

import bowling.Pin;
import bowling.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("다수 볼링 게임 테스트")
public class BowlingGamesTests {

    private BowlingGames bowlingGames = BowlingGames.newInstance("AAA", "BBB");

    @DisplayName("생성 테스트")
    @Test
    public void generateTest() {
        assertThatCode(() -> BowlingGames.newInstance("AAA", "BBB")).doesNotThrowAnyException();
    }

    @DisplayName("사용자 투구 테스트")
    @Test
    public void getNextTurnTest() {
        assertThatCode(() -> bowlingGames.bowl(Player.of("AAA"), Pin.ofMax())).doesNotThrowAnyException();
    }
}
