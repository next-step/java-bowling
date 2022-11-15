package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.domain.score.Score;
import bowling.domain.score.TotalScore;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DefaultFrameTest {

    private static DefaultFrame createDefaultFrame(int... ints) {
        DefaultFrame defaultFrame = new DefaultFrame();
        Arrays.stream(ints).forEach(i -> defaultFrame.addScore(Score.of(i)));
        return defaultFrame;
    }

    private static DefaultFrame setBonusScore(DefaultFrame frame, int... ints) {
        Arrays.stream(ints).forEach(i -> frame.addBonusScore(Score.of(i)));
        return frame;
    }

    @DisplayName("첫 번째 점수와 두 번째 점수의 합이 10을 넘으면 IllegalArgumentException 예외를 throw 한다.")
    @Test
    void validate_sum() {
        DefaultFrame defaultFrame = createDefaultFrame(5);

        assertThatThrownBy(() -> defaultFrame.addScore(Score.of(6))).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("첫번째 시도가 스트라이크면 false를 반환한다.")
    @Test
    void is_not_remain_chance_strike() {
        assertThat(createDefaultFrame(10).isRemainChance()).isFalse();
    }

    @DisplayName("첫번째 시도가 스트라이크가 아니고 두 점수의 합이 10을 넘으면 IllegalArgumentException 예외를 throw 한다.")
    @Test
    void is_not_remain_chance_size() {
        Frame frame = createDefaultFrame(9);

        assertThatThrownBy(() -> frame.addScore(Score.of(2))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void is_not_end_score_aggregation_default() {
        Frame frame1 = createDefaultFrame();
        Frame frame2 = createDefaultFrame(9);
        Frame frame3 = createDefaultFrame(8, 1);

        Assertions.assertAll(
                () -> assertThat(frame1.isNotEndScoreAggregation()).isTrue(),
                () -> assertThat(frame2.isNotEndScoreAggregation()).isTrue(),
                () -> assertThat(frame3.isNotEndScoreAggregation()).isFalse()
        );
    }

    @Test
    void is_not_end_score_aggregation_spare() {
        Frame frame1 = createDefaultFrame(9, 1);
        Frame frame2 = setBonusScore(createDefaultFrame(9, 1), 1);

        Assertions.assertAll(
                () -> assertThat(frame1.isNotEndScoreAggregation()).isTrue(),
                () -> assertThat(frame2.isNotEndScoreAggregation()).isFalse()
        );
    }

    @Test
    void is_not_end_score_aggregation_strike() {
        Frame frame1 = createDefaultFrame(10);
        Frame frame2 = setBonusScore(createDefaultFrame(10), 10);
        Frame frame3 = setBonusScore(createDefaultFrame(10), 10, 10);

        Assertions.assertAll(
                () -> assertThat(frame1.isNotEndScoreAggregation()).isTrue(),
                () -> assertThat(frame2.isNotEndScoreAggregation()).isTrue(),
                () -> assertThat(frame3.isNotEndScoreAggregation()).isFalse()
        );
    }

    @Test
    void totalScoreTest() {
        Frame frame = createDefaultFrame(8);
        TotalScore expected = TotalScore.defaultFrameTotalScore();
        expected.addRegularScore(Score.of(8));

        assertThat(frame.totalScore()).isEqualTo(expected);
    }
}
