package bowling.step4;

import bowling.step4.domain.frame.FinalFrame;
import bowling.step4.domain.frame.Frame;
import bowling.step4.domain.frame.NormalFrame;
import bowling.step4.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FinalFrameTest {

    @Test
    @DisplayName("마지막 프레임 생성")
    void create_final() {
        FinalFrame finalFrame = new FinalFrame(10);
        assertThat(finalFrame).isEqualTo(new FinalFrame(10));
    }

    @ParameterizedTest
    @DisplayName("마지막 프레임 유효성 검사")
    @ValueSource(ints = {1, 9, 0, -1})
    void create_final_invalid(int frameNo) {
        assertThatThrownBy(() -> new FinalFrame(frameNo))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("마지막 프레임 pitch - 스트라이크or스페어시 3번 투구")
    void create_finalframe_pitch_strike() {
        FinalFrame finalFrame = new FinalFrame(10);
        finalFrame.pitch(10);
        assertThat(finalFrame.isGameEnd()).isFalse();
        finalFrame.pitch(5);
        assertThat(finalFrame.isGameEnd()).isFalse();
        finalFrame.pitch(4);
        assertThat(finalFrame.isGameEnd()).isTrue();
    }

    @Test
    @DisplayName("마지막 프레임 pitch - 스트라이크or스페어 아닐시 2번 투구")
    void create_finalframe_pitch_nostrike() {
        FinalFrame finalFrame = new FinalFrame(10);
        finalFrame.pitch(8);
        finalFrame.pitch(1);
        assertThat(finalFrame.isGameEnd()).isTrue();
    }

    @Test
    @DisplayName("9번 프레임 + 마지막 프레임 점수계산")
    void finalframe_calculate_strike() {
        Frame nineFrame = new NormalFrame(9);
        Frame finalFrame = nineFrame.pitch(10);

        assertThat(nineFrame.getScore()).isEqualTo(Score.readyScore());

        finalFrame.pitch(5);
        assertThat(nineFrame.getScore()).isEqualTo(Score.readyScore());

        finalFrame.pitch(4);
        assertThat(nineFrame.getScore()).isEqualTo(new Score(19, 0));
    }

    @Test
    @DisplayName("마지막 프레임 점수계산")
    void finalframe_calculate() {
        Score score = new FinalFrame(10).pitch(10).pitch(10).pitch(10).getScore();
        assertThat(score).isEqualTo(new Score(30, 0));

        Score score2 = new FinalFrame(10).pitch(3).pitch(7).pitch(10).getScore();
        assertThat(score2).isEqualTo(new Score(20, 0));

        Score score3 = new FinalFrame(10).pitch(4).pitch(2).getScore();
        assertThat(score3).isEqualTo(new Score(6, 0));
    }
}
