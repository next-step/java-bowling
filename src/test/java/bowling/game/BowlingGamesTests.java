package bowling.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("다수 볼링 게임 테스트")
public class BowlingGamesTests {

    @DisplayName("생성 테스트")
    @Test
    public void generateTest() {
        assertThatCode(() -> BowlingGames.newInstance("AAA", "BBB")).doesNotThrowAnyException();
    }
}
