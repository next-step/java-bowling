package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.domain.score.Score;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DefaultFrameTest {

    @DisplayName("첫 번째 점수와 두 번째 점수의 합이 10을 넘으면 IllegalArgumentException 예외를 throw 한다.")
    @Test
    void validate_sum() {
        DefaultFrame defaultFrame = new DefaultFrame();
        defaultFrame.addScore(Score.of(5));

        assertThatThrownBy(() -> defaultFrame.addScore(Score.of(6))).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("첫번째 시도가 스트라이크면 false를 반환한다.")
    @Test
    void is_not_remain_chance_strike() {
        Frame strikeFrame = new DefaultFrame();
        strikeFrame.addScore(Score.of(10));

        assertThat(strikeFrame.isRemainChance()).isFalse();
    }

    @DisplayName("첫번째 시도가 스트라이크가 아니고 두 점수의 합이 10을 넘으면 IllegalArgumentException 예외를 throw 한다.")
    @Test
    void is_not_remain_chance_size() {
        Frame frame = new DefaultFrame();
        frame.addScore(Score.of(9));
        assertThatThrownBy(() -> frame.addScore(Score.of(2))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void is_not_end_score_aggregation_default() {
        Frame frame1 = new DefaultFrame();
        Frame frame2 = new DefaultFrame();
        frame2.addScore(Score.of(9));
        Frame frame3 = new DefaultFrame();
        frame3.addScore(Score.of(8));
        frame3.addScore(Score.of(1));

        Assertions.assertAll(
                () -> assertThat(frame1.isNotEndScoreAggregation()).isTrue(),
                () -> assertThat(frame2.isNotEndScoreAggregation()).isTrue(),
                () -> assertThat(frame3.isNotEndScoreAggregation()).isFalse()
        );
    }

    @Test
    void is_not_end_score_aggregation_spare() {
        Frame frame1 = new DefaultFrame();
        frame1.addScore(Score.of(9));
        frame1.addScore(Score.of(1));
        Frame frame2 = new DefaultFrame();
        frame2.addScore(Score.of(9));
        frame2.addScore(Score.of(1));
        frame2.addBonusScore(Score.of(1));
        Assertions.assertAll(
                () -> assertThat(frame1.isNotEndScoreAggregation()).isTrue(),
                () -> assertThat(frame2.isNotEndScoreAggregation()).isFalse()
        );
    }

    @Test
    void is_not_end_score_aggregation_strike() {
        Frame frame1 = new DefaultFrame();
        frame1.addScore(Score.of(10));
        Frame frame2 = new DefaultFrame();
        frame2.addScore(Score.of(10));
        frame2.addBonusScore(Score.of(10));
        Frame frame3 = new DefaultFrame();
        frame3.addScore(Score.of(10));
        frame3.addBonusScore(Score.of(10));
        frame3.addBonusScore(Score.of(10));
        Assertions.assertAll(
                () -> assertThat(frame1.isNotEndScoreAggregation()).isTrue(),
                () -> assertThat(frame2.isNotEndScoreAggregation()).isTrue(),
                () -> assertThat(frame3.isNotEndScoreAggregation()).isFalse()
        );
    }
}
