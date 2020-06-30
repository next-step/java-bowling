package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingGamesTests {
    @DisplayName("BowlingGame 리스트를 주입받아서 객체를 생성할 수 있다.")
    @Test
    void createTest() {
        List<BowlingGame> bowlingGameList = Arrays.asList(BowlingGame.start("jbj"), BowlingGame.start("jyn"));

        BowlingGames bowlingGames = BowlingGames.of(bowlingGameList);

        assertThat(bowlingGames.size()).isEqualTo(2);
    }
}
