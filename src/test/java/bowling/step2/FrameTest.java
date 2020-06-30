package bowling.step2;

import bowling.step2.domain.frame.FinalFrame;
import bowling.step2.domain.frame.NormalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FrameTest {

    @ParameterizedTest
    @DisplayName("일반 프레임 생성(+다음 프레임 생성)")
    @ValueSource(ints = {1,2,3,5,7})
    void create(int frameNo) {
        NormalFrame normalFrame = new NormalFrame(frameNo);
        assertThat(normalFrame).isEqualTo(new NormalFrame(frameNo));
        assertThat(normalFrame.nextFrame()).isEqualTo(new NormalFrame(frameNo + 1));
    }

    @Test
    @DisplayName("일반 프레임 생성 - 9프레임에서 다음 프레임 진행시에는 FinalFrame으로")
    void create_normal_last() {
        NormalFrame normalFrame = new NormalFrame(9);
        assertThat(normalFrame.nextFrame()).isEqualTo(new FinalFrame(10));
    }

    @Test
    @DisplayName("마지막 프레임 생성")
    void create_final() {
        FinalFrame finalFrame = new FinalFrame(10);
        assertThat(finalFrame).isEqualTo(new FinalFrame(10));
    }

    @ParameterizedTest
    @DisplayName("일반 프레임 유효성 검사")
    @ValueSource(ints = {-1, 10, 0})
    void create_normal_invalid(int frameNo) {
        assertThatThrownBy(() -> new NormalFrame(frameNo))
                    .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("마지막 프레임 유효성 검사")
    @ValueSource(ints = {1, 9, 0, -1})
    void create_final_invalid(int frameNo) {
        assertThatThrownBy(() -> new FinalFrame(frameNo))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("일반 - 다음 프레임 진행을 위해 투구수에 따른 현재 프레임 종료여부 메소드 테스트")
    void nextFrame_condition_normal() {
        NormalFrame normalFrame = new NormalFrame(2);
        normalFrame.pitch(3);
        assertThat(normalFrame.pitchesOver()).isFalse();
        normalFrame.pitch(4);
        assertThat(normalFrame.pitchesOver()).isTrue();
    }

    @Test
    @DisplayName("일반 - 현재 프레임 종료여부[스트라이크의 경우]")
    void nextFrame_condition_normal_strike_case() {
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.pitch(10);
        assertThat(normalFrame.pitchesOver()).isTrue();
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
    @DisplayName("마지막 프레임 pitch - 스트라이크or스페어시 3번 투구")
    void create_finalframe_pitch_strike() {
        FinalFrame finalFrame = new FinalFrame(10);
        finalFrame.pitch(10);
        finalFrame.pitch(5);
        assertThat(finalFrame.pitchesOver()).isFalse();
        finalFrame.pitch(4);
        assertThat(finalFrame.pitchesOver()).isTrue();
    }

    @Test
    @DisplayName("마지막 프레임 pitch - 스트라이크or스페어 아닐시 2번 투구")
    void create_finalframe_pitch_nostrike() {
        FinalFrame finalFrame = new FinalFrame(10);
        finalFrame.pitch(8);
        finalFrame.pitch(1);
        assertThat(finalFrame.pitchesOver()).isTrue();
    }
}
