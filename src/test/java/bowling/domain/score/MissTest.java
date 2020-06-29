package bowling.domain.score;

import bowling.domain.frame.Point;
import bowling.domain.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class MissTest {

    @Test
    @DisplayName("정상적인 MISS")
    void nextScore() {
        Miss miss = new Miss(Point.inputPoint(5));
        String score = miss.getScore();
        assertThat(score).isEqualTo("5");
    }


    @Test
    @DisplayName("MISS 10, 0 에러 테스트")
    void nextScoreFailTest() {
        assertThatThrownBy(() -> {
            new Miss(Point.inputPoint(10));
        }).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> {
            new Miss(Point.inputPoint(0));
        }).isInstanceOf(IllegalArgumentException.class);
    }


}
