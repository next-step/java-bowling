package bowling;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.FrameRound;
import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FrameTest {

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6,7,8,9})
    @DisplayName("Normal 프레임에 대한 테스트")
    void NormalFrameTest(int number) {

        FrameRound frameRound = FrameRound.get(number);

        Frame frame = NormalFrame.get(frameRound);
        assertThat(frame).isEqualTo(new NormalFrame(frameRound));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1:2:3",
            "3:5:8",
            "5:5:10",
            "10:0:10"
    }, delimiter = ':')
    @DisplayName("기본 프레임에서 핀 Sum 값 테스트")
    void normalSumTest(int first, int second, int sum) {
        Frame frame = NormalFrame.get(FrameRound.start());
        frame.pitch(first);
        frame.pitch(second);

        assertThat(frame.pinCountSum()).isEqualTo(sum);
    }

    @Test
    @DisplayName("기본 투구수가 3회일 경우 에러발생 테스트")
    void normalFrameOverMaxPitchCountException() {
        assertThatThrownBy(() -> {
            Frame frame = NormalFrame.get(FrameRound.start());
            frame.pitch(1);
            frame.pitch(1);
            frame.pitch(1);
        });
    }

    @Test
    @DisplayName("핀의 개수가 10개 초과할 경우 에러발생 테스트")
    void normalFrameOverMaxPinCountException() {
        assertThatThrownBy(() -> {
            Frame frame = NormalFrame.get(FrameRound.start());
            frame.pitch(4);
            frame.pitch(8);
        });
    }

    @Test
    @DisplayName("마지막 프레임의 투구수가 4회일 경우 에러발생 테스트")
    void finalFrameOverMaxPitchCountException() {
        assertThatThrownBy(() -> {
            Frame frame = FinalFrame.get();
            frame.pitch(1);
            frame.pitch(1);
            frame.pitch(1);
            frame.pitch(1);
        });
    }

    @Test
    @DisplayName("마지막 프레임의 핀의 개수가 30개 오버된 경우 에러발생 테스트")
    void finalFrameOverMaxPinCountException() {
        assertThatThrownBy(() -> {
            Frame frame = FinalFrame.get();
            frame.pitch(31);
        });
    }

    @Test
    @DisplayName("Final 프레임에 대한 테스트")
    void finalFrameTest() {
        FrameRound frameRound = FrameRound.get(10);
        Frame frame = FinalFrame.get();
        assertThat(frame).isEqualTo(new FinalFrame(frameRound));
    }
}
