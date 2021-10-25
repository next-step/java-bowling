package bowling.model;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BowlingGameTest {
    @Test
    public void 모든_플레이어가_투구를_마치면_게임_끝남() {
        BowlingGame bowlingGame = new BowlingGame(Arrays.asList("aaa", "bbb"));

        for (int i = 0; i < 24; i++) {
            bowlingGame.bowl(10);
        }

        Assertions.assertTrue(bowlingGame.isEndGame());
    }

    @Test
    public void 한_프레임이_끝나면_본인차례_끝난다() {
        BowlingGame bowlingGame = new BowlingGame(Arrays.asList("aaa", "bbb"));
        bowlingGame.bowl(10);
        assertThat(bowlingGame).isEqualTo(new BowlingGame(Arrays.asList("bbb", "aaa")));
    }
}
