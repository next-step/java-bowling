package bowling.domain;

import bowling.domain.frame.round.NormalRound;
import bowling.domain.score.BonusScore;
import bowling.domain.score.Point;
import bowling.domain.score.Score;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BonusScoreTest {

    @DisplayName("10라운드가 아니면 보너스 투구를 할 수 없다.")
    @Test
    void case1() {
        Assertions.assertThatThrownBy(() -> {
            BonusScore.of(NormalRound.of(9), Score.of(Point.of(5), Point.of(5)), Point.of(5));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("스패어나 스트라이크가 아니면 보너스 투구를 할 수 없다..")
    @Test
    void case2() {
        Assertions.assertThatThrownBy(() -> {
            BonusScore.of(NormalRound.of(10), Score.of(Point.of(5), Point.of(4)), Point.of(5));
        }).isInstanceOf(IllegalArgumentException.class);
    }
}