package bowling.game;

import bowling.Pin;
import bowling.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

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

    @DisplayName("사용자 이름 반환 테스트")
    @Test
    public void getPlayerNames() {
        assertThat(bowlingGames.getPlayers()).isEqualTo(Arrays.asList("AAA", "BBB"));
    }
}
