package bowling.domain.frame;

import bowling.bowlingexception.IllegalFrameRecordException;
import bowling.bowlingexception.IllegalPinRangeException;
import bowling.bowlingexception.InvalidScoreCalculationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NormalFrameTest {

    @Test
    @DisplayName("프레임에 쓰러진 핀 갯수 기록")
    void recordDownedPins() {
        NormalFrame frame = new NormalFrame();

        frame.record(6);
    }

    @Test
    @DisplayName("프레임은 최대 2회의 기록을 가질 수 있다")
    void recordTwice() {
        NormalFrame frame = new NormalFrame();

        assertThat(frame.isEnd()).isFalse();
        frame.record(4);
        assertThat(frame.isEnd()).isFalse();
        frame.record(5);
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("프레임이 종료 이후에도 record를 시도할 시에 예외 처리")
    void exceptionAfterTwoRecord() {
        NormalFrame frame = new NormalFrame();

        frame.record(4);
        frame.record(5);

        assertThatThrownBy(
                () -> frame.record(3)
        ).isInstanceOf(IllegalFrameRecordException.class);
    }

    @Test
    @DisplayName("스트라이크 상태의 종료 조건 테스트")
    void isEndWhenStrike() {
        NormalFrame frame = new NormalFrame();

        assertThat(frame.isEnd()).isFalse();
        frame.record(10);
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("스트라이크 이후 입력의 예외처리 테스트")
    void exceptionAfterStrike() {
        NormalFrame frame = new NormalFrame();

        frame.record(10);

        assertThatThrownBy(
                () -> frame.record(3)
        ).isInstanceOf(IllegalFrameRecordException.class);
    }

    @ValueSource(ints = {-1, 11})
    @DisplayName("1회차 범위는 0 ~ 10 넘을 수 없음")
    @ParameterizedTest
    void validateInput(int input) {
        NormalFrame normalFrame = new NormalFrame();

        assertThatThrownBy(
                () -> normalFrame.record(input)
        ).isInstanceOf(IllegalPinRangeException.class);
    }

    @Test
    @DisplayName("2회차 합이 0 ~ 10을 만족해야 됨")
    void sumIsUnder10() {
        NormalFrame normalFrame = new NormalFrame();

        normalFrame.record(6);

        assertThatThrownBy(
                () -> normalFrame.record(5)
        ).isInstanceOf(IllegalPinRangeException.class);
    }

    private static Stream<Arguments> oneRecordScenario() {
        return Stream.of(
                Arguments.of(0, "-"),
                Arguments.of(1, "1"),
                Arguments.of(9, "9"),
                Arguments.of(10, "X")
        );
    }

    private static Stream<Arguments> twoRecordScenario() {
        return Stream.of(
                Arguments.of(0, 10, "- | /"),
                Arguments.of(3, 7, "3 | /"),
                Arguments.of(3, 6, "3 | 6"),
                Arguments.of(0, 0, "- | -"),
                Arguments.of(0, 4, "- | 4")
        );
    }

    @MethodSource("oneRecordScenario")
    @ParameterizedTest
    void DescriptionOfFrame(int downedPin, String expected) {
        NormalFrame frame = new NormalFrame();
        frame.record(downedPin);

        assertThat(frame.getDescriptionForm()).isEqualTo(expected);
    }

    @MethodSource("twoRecordScenario")
    @ParameterizedTest
    void DescriptionOfFrameTwoRecord(int firstPitch, int secondPitch, String expected) {
        NormalFrame frame = new NormalFrame();
        frame.record(firstPitch);
        frame.record(secondPitch);

        assertThat(frame.getDescriptionForm()).isEqualTo(expected);
    }

    @Test
    @DisplayName("프레임이 종료되지 않은 상태일 때의 스코어 반환 요청 예외처리")
    void scoringWhenItCannotBeCalculated() {
        NormalFrame firstFrame = new NormalFrame();
        assertThatThrownBy(
                firstFrame::calculateScore
        ).isInstanceOf(InvalidScoreCalculationException.class);
    }

    @Test
    @DisplayName("MISS 상태의 계산")
    void calculateScoreOnMiss() {
        NormalFrame frame = new NormalFrame();
        frame.record(3);
        frame.record(5);

        assertThat(frame.calculateScore())
                .isEqualTo(8);
    }

    @Test
    @DisplayName("Spare 상태의 계산")
    void calculateScoreMiss() {
        NormalFrame firstFrame = new NormalFrame();
        firstFrame.record(3);
        firstFrame.record(7);

        NormalFrame secondFrame = new NormalFrame();
        secondFrame.record(8);

        assertThat(firstFrame.calculateScore())
                .isEqualTo(18);
    }

    @Test
    @DisplayName("Frame 완료(Miss, Spare, Strike) 시 새로운 Frame 반환")
    void createNextFrame() {
        Frame firstFrame = new NormalFrame();
        Frame currentFrame = firstFrame;

        currentFrame = currentFrame.record(4);
        assertThat(currentFrame).isEqualTo(firstFrame);
        currentFrame = currentFrame.record(3);
        assertThat(currentFrame).isNotEqualTo(firstFrame);
    }
}
