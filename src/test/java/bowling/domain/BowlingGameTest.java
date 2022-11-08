package bowling.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BowlingGameTest {

    @DisplayName("볼링 게임 점수를 추가한다.")
    @Test
    void play() {
        BowlingGame bowlingGame = new BowlingGame(new Scoreboard(new Name("cys")));
        Scoreboard expected = new Scoreboard(new Name("cys"));
        expected.addScore(Score.of(1), new Round(1));

        Assertions.assertThat(bowlingGame.play(Score.of(1))).isEqualTo(expected);
    }
}
