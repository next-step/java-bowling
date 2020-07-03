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

public class NormalFrameTest {

    @ParameterizedTest
    @DisplayName("일반 프레임 생성 -투구시 다음 프레임 생성")
    @ValueSource(ints = {1,2,3,5,7})
    void create(int frameNo) {
        Frame normalFrame_strike = new NormalFrame(frameNo).pitch(10);
        assertThat(normalFrame_strike.getframeNo()).isEqualTo(frameNo + 1);

        Frame normalFrame_no_strike = new NormalFrame(frameNo).pitch(8).pitch(1);
        assertThat(normalFrame_no_strike.getframeNo()).isEqualTo(frameNo + 1);;
    }

    @Test
    @DisplayName("일반 프레임 생성 - 9프레임에서 다음 프레임 진행시에는 FinalFrame으로")
    void create_normal_last() {
        Frame normalFrame = new NormalFrame(9).pitch(10);
        assertThat(normalFrame).isEqualTo(new FinalFrame(10));
    }

    @ParameterizedTest
    @DisplayName("일반 프레임 유효성 검사")
    @ValueSource(ints = {-1, 10, 0})
    void create_normal_invalid(int frameNo) {
        assertThatThrownBy(() -> new NormalFrame(frameNo))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("일반 - 다음 프레임 진행을 위해 투구수에 따른 현재 프레임 종료여부 메소드 테스트")
    void nextFrame_condition_normal() {
        NormalFrame normalFrame = new NormalFrame(2);
        normalFrame.pitch(3);
        assertThat(normalFrame.getState().isFinish()).isFalse();
        normalFrame.pitch(4);
        assertThat(normalFrame.getState().isFinish()).isTrue();
    }

    @Test
    @DisplayName("일반 - 현재 프레임 종료여부[스트라이크의 경우]")
    void nextFrame_condition_normal_strike_case() {
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.pitch(10);
        assertThat(normalFrame.getState().isFinish()).isTrue();
    }

    @ParameterizedTest
    @DisplayName("일반 - 프레임 pitch 유효성 검사")
    @ValueSource(ints = {11, -1, 12})
    void normal_pitch_invalid(int inputPitch) {
        NormalFrame normalFrame = new NormalFrame(1);
        assertThatThrownBy(() -> normalFrame.pitch(inputPitch))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("일반 - 프레임 pitch 유효성 검사2")
    @ValueSource(ints = {11, -1, 12})
    void normal_pitch_invalid_try2(int inputPitch) {
        NormalFrame normalFrame = new NormalFrame(1);
        assertThatThrownBy(() -> normalFrame.pitch(inputPitch))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("일반 - 프레임 점수계산")
    void normal_frame_calculate_score() {
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.pitch(5);
        normalFrame.pitch(3);
        Score score = normalFrame.getScore();
        assertThat(score).isEqualTo(new Score(8,0));

        NormalFrame normalFrame2 = new NormalFrame(1);
        normalFrame2.pitch(2);
        normalFrame2.pitch(5);
        Score score2 = normalFrame2.getScore();
        assertThat(score2).isEqualTo(new Score(7,0));
    }

    @Test
    @DisplayName("일반 - 프레임 점수계산_스트라이크or스페어 [스트라이크나 스페어시 바로 점수계산이 되지 않으므로 준비점수 리턴]")
    void normal_frame_calculate_score_has_strike_or_spare() {
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.pitch(10);
        Score score = normalFrame.getScore();
        assertThat(score).isEqualTo(Score.readyScore());

        NormalFrame normalFrame2 = new NormalFrame(1);
        normalFrame2.pitch(5);
        normalFrame2.pitch(5);
        Score score2 = normalFrame2.getScore();
        assertThat(score2).isEqualTo(Score.readyScore());
    }
}
