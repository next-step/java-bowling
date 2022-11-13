package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.domain.score.Score;
import bowling.domain.score.TotalScore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LastFrameTest {

    @DisplayName("스트라이크가 아니고 첫 번째 점수와 두 번째 점수의 합이 10을 넘으면 IllegalArgumentException 예외를 throw 한다.")
    @Test
    void validate_sum() {
        LastFrame lastFrame = new LastFrame();
        lastFrame.addScore(Score.of(9));

        assertThatThrownBy(() -> lastFrame.addScore(Score.of(10))).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("첫 번째 시도가 스트라이크가 아니고 두번째 프레임 결과가 스페어가 아니면 false를 반환한다.")
    @Test
    void is_remain_chance() {
        LastFrame firstStrikeFrame = new LastFrame();
        firstStrikeFrame.addScore(Score.of(8));
        firstStrikeFrame.addScore(Score.of(1));

        assertThatThrownBy(() -> firstStrikeFrame.addScore(Score.of(10))).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("첫 번째 시도가 스트라이크이고 두번째 시도와 세번째 시도의 결과가 10 이상이면 IllegalArgumentException 예외를 throw 한다.")
    @Test
    void second_third_sum() {
        LastFrame firstStrikeFrame = new LastFrame();
        firstStrikeFrame.addScore(Score.of(10));
        firstStrikeFrame.addScore(Score.of(9));

        assertThatThrownBy(() -> firstStrikeFrame.addScore(Score.of(2))).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("첫 번째 시도와 두번째 시도가 스페어이면 return한다.")
    @Test
    void second_spare() {
        LastFrame spareFrame = new LastFrame();
        spareFrame.addScore(Score.of(8));
        spareFrame.addScore(Score.of(2));

        Assertions.assertDoesNotThrow(() -> spareFrame.addScore(Score.of(10)));
    }

    @Test
    void is_not_end_score_aggregation_default() {
        Frame frame1 = new LastFrame();
        frame1.addScore(Score.of(8));
        Frame frame2 = new LastFrame();
        frame2.addScore(Score.of(8));
        frame2.addScore(Score.of(1));

        Assertions.assertAll(
                () -> assertThat(frame1.isNotEndScoreAggregation()).isTrue(),
                () -> assertThat(frame2.isNotEndScoreAggregation()).isFalse()
        );
    }

    @Test
    void is_not_end_score_aggregation_spare() {
        Frame spareFrame1 = new LastFrame();
        spareFrame1.addScore(Score.of(8));
        spareFrame1.addScore(Score.of(2));
        Frame spareFrame2 = new LastFrame();
        spareFrame2.addScore(Score.of(8));
        spareFrame2.addScore(Score.of(2));
        spareFrame2.addScore(Score.of(2));

        Assertions.assertAll(
                () -> assertThat(spareFrame1.isNotEndScoreAggregation()).isTrue(),
                () -> assertThat(spareFrame2.isNotEndScoreAggregation()).isFalse()
        );
    }

    @Test
    void is_not_end_score_aggregation_strike() {
        Frame strikeFrame1 = new LastFrame();
        strikeFrame1.addScore(Score.of(10));
        Frame strikeFrame2 = new LastFrame();
        strikeFrame2.addScore(Score.of(10));
        strikeFrame2.addScore(Score.of(2));
        Frame strikeFrame3 = new LastFrame();
        strikeFrame3.addScore(Score.of(10));
        strikeFrame3.addScore(Score.of(2));
        strikeFrame3.addScore(Score.of(2));

        Assertions.assertAll(
                () -> assertThat(strikeFrame3.isNotEndScoreAggregation()).isFalse()
        );
    }

    @Test
    void is_not_end_score() {
        TotalScore endScore = TotalScore.lastFrameTotalScore();
        endScore.addRegularScore(Score.of(10));
        endScore.addRegularScore(Score.of(10));
        endScore.addRegularScore(Score.of(10));

        TotalScore notEndScore = TotalScore.lastFrameTotalScore();
        notEndScore.addRegularScore(Score.of(2));

        Assertions.assertAll(
                () -> assertThat(endScore.regularScores().isNotEndScore(endScore.regularScores())).isFalse(),
                () -> assertThat(notEndScore.regularScores().isNotEndScore(notEndScore.regularScores())).isTrue()
        );
    }

    @Test
    void totalScoreTest() {
        Frame frame = new LastFrame();
        frame.addScore(Score.of(8));
        TotalScore expected = TotalScore.lastFrameTotalScore();
        expected.addRegularScore(Score.of(8));

        assertThat(frame.totalScore()).isEqualTo(expected);
    }
}
