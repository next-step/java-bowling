package bowling.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FrameTest {

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6,7,8,9})
    @DisplayName("NormalFrame 생성후 객체 확인")
    public void frame_normalFrame_isEqualTo(int number) {
        // given
        FrameNumber frameNumber = FrameNumber.from(number);

        // when
        Frame frame = NormalFrame.from(frameNumber);

        // then
        assertThat(frame).isEqualTo(NormalFrame.from(frameNumber));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "4:5:9",
            "5:5:10",
            "10:0:10",
            "4:4:8"
    }, delimiter = ':')
    @DisplayName("NormalFrame Pin 갯수 합계 확인")
    public void frame_normalFrameSumPinCount_isEqualTo(int n1, int n2, int sum) {
        // given
        Frame frame = NormalFrame.from(FrameNumber.init());

        // when
        frame.pitch(n1);
        frame.pitch(n2);

        // then
        assertThat(frame.getSumPinCount()).isEqualTo(sum);
    }

    @Test
    @DisplayName("기본 프레임의 투구 횟수가 2회를 넘어 간경우 IllegalArgumentException 발생")
    public void frame_normalFrameOverPitchCount_throwException() {
        assertThatThrownBy(() -> {
            Frame frame = NormalFrame.from(FrameNumber.init());
            frame.pitch(5);
            frame.pitch(4);
            frame.pitch(1);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NormalFrame.MAX_PITCH_COUNT_OVER);
    }

    @Test
    @DisplayName("기본 프레임의 Pin 갯수가 10개를 넘어 간경우 IllegalArgumentException 발생")
    public void frame_normalFrameOverPinCount_throwException() {
        assertThatThrownBy(() -> {
            Frame frame = NormalFrame.from(FrameNumber.init());
            frame.pitch(11);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NormalFrame.MAX_PIN_COUNT_OVER);
    }

    @Test
    @DisplayName("마지막 프레임의 투구 횟수가 3회를 넘어 간경우 IllegalArgumentException 발생")
    public void frame_finalFrameOverPitchCount_throwException() {
        assertThatThrownBy(() -> {
            Frame frame = FinalFrame.from();
            frame.pitch(5);
            frame.pitch(4);
            frame.pitch(1);
            frame.pitch(4);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(FinalFrame.MAX_FINAL_PITCH_COUNT_OVER);
    }

    @Test
    @DisplayName("마지막 프레임의 Pin 갯수가 30개를 넘어 간경우 IllegalArgumentException 발생")
    public void frame_finalFrameOverPinCount_throwException() {
        assertThatThrownBy(() -> {
            Frame frame = FinalFrame.from();
            frame.pitch(31);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(FinalFrame.MAX_FINAL_PIN_COUNT_OVER);
    }
}
