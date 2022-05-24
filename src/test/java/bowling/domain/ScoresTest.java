package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoresTest {
    @Test
    @DisplayName("초기 점수판")
    void initPlayFrame() {
        Scores scores = new Scores(Arrays.asList(
                new Score(),
                new Score()
        ));
        assertThat(scores.playFrame(new PlayerName("tst"))).isEqualTo("| tst  |      |      |  ");
    }

    @Test
    @DisplayName("스트라이크, 스페어, 미스, 거터 올바른 출력")
    void properPlayFrame() {
        Scores scores = new Scores(Arrays.asList(
                new Score(10),
                new Score(9,1),
                new Score(8,1),
                new Score(1,0),
                new Score(0,1),
                new Score(0,0)
        ));
        assertThat(scores.playFrame(new PlayerName("tst"))).isEqualTo("| tst  |  X   |  9|/ |  8|1 |  1|- |  -|1 |  -|- |  ");
    }
}
