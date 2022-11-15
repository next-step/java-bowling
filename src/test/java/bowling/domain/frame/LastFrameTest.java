package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.domain.score.Score;
import bowling.domain.score.TotalScore;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LastFrameTest {

    private static LastFrame createLastFrame(int... ints) {
        LastFrame lastFrame = new LastFrame();
        Arrays.stream(ints).forEach(i -> lastFrame.addScore(Score.of(i)));
        return lastFrame;
    }

    @DisplayName("스트라이크가 아니고 첫 번째 점수와 두 번째 점수의 합이 10을 넘으면 IllegalArgumentException 예외를 throw 한다.")
    @Test
    void validate_sum() {
        assertThatThrownBy(() -> createLastFrame(8).addScore(Score.of(10)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("첫 번째 시도가 스트라이크가 아니고 두번째 프레임 결과가 스페어가 아니면 false를 반환한다.")
    @Test
    void is_remain_chance() {
        assertThatThrownBy(() -> createLastFrame(8, 1).addScore(Score.of(10)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("첫 번째 시도가 스트라이크이고 두번째 시도와 세번째 시도의 결과가 10 이상이면 IllegalArgumentException 예외를 throw 한다.")
    @Test
    void second_third_sum() {
        assertThatThrownBy(() -> createLastFrame(10, 9).addScore(Score.of(2)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("첫 번째 시도와 두번째 시도가 스페어이면 return한다.")
    @Test
    void second_spare() {
        Assertions.assertDoesNotThrow(() -> createLastFrame(8, 2).addScore(Score.of(10)));
    }

    @Test
    void is_not_end_score_aggregation_default() {
        Assertions.assertAll(
                () -> assertThat(createLastFrame(8).isNotEndScoreAggregation()).isTrue(),
                () -> assertThat(createLastFrame(8, 1).isNotEndScoreAggregation()).isFalse()
        );
    }

    @Test
    void is_not_end_score_aggregation_spare() {
        Assertions.assertAll(
                () -> assertThat(createLastFrame(8, 2).isNotEndScoreAggregation()).isTrue(),
                () -> assertThat(createLastFrame(8, 2, 2).isNotEndScoreAggregation()).isFalse()
        );
    }

    @Test
    void is_not_end_score_aggregation_strike() {
        Assertions.assertAll(
                () -> assertThat(createLastFrame(10).isNotEndScoreAggregation()).isTrue(),
                () -> assertThat(createLastFrame(10, 2).isNotEndScoreAggregation()).isTrue(),
                () -> assertThat(createLastFrame(10, 2, 2).isNotEndScoreAggregation()).isFalse()
        );
    }


    @Test
    void totalScoreTest() {
        Frame frame = createLastFrame(8);
        TotalScore expected = TotalScore.lastFrameTotalScore();
        expected.addRegularScore(Score.of(8));

        assertThat(frame.totalScore()).isEqualTo(expected);
    }
}
