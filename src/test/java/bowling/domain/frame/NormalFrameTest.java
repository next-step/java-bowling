package bowling.domain.frame;

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
        NormalFrame frame = (NormalFrame) Frame.createInitialFrame();

        frame.record(6);
    }

    @Test
    @DisplayName("프레임은 최대 2회의 기록을 가질 수 있다")
    void recordTwice() {
        NormalFrame frame = (NormalFrame) Frame.createInitialFrame();

        assertThat(frame.isEnd()).isFalse();
        frame.record(4);
        assertThat(frame.isEnd()).isFalse();
        frame.record(5);
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("스트라이크 상태의 종료 조건 테스트")
    void isEndWhenStrike() {
        NormalFrame frame = (NormalFrame) Frame.createInitialFrame();

        assertThat(frame.isEnd()).isFalse();
        frame.record(10);
        assertThat(frame.isEnd()).isTrue();
    }

    @ValueSource(ints = {-1, 11})
    @DisplayName("1회차 범위는 0 ~ 10 넘을 수 없음")
    @ParameterizedTest
    void validateInput(int input) {
        NormalFrame normalFrame = (NormalFrame) Frame.createInitialFrame();

        assertThatThrownBy(
                () -> normalFrame.record(input)
        ).isInstanceOf(IllegalPinRangeException.class);
    }

    @Test
    @DisplayName("2회차 합이 0 ~ 10을 만족해야 됨")
    void sumIsUnder10() {
        NormalFrame normalFrame = (NormalFrame) Frame.createInitialFrame();

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
        NormalFrame frame = (NormalFrame) Frame.createInitialFrame();
        frame.record(downedPin);

        assertThat(frame.getDescriptionForm()).isEqualTo(expected);
    }

    @MethodSource("twoRecordScenario")
    @ParameterizedTest
    void DescriptionOfFrameTwoRecord(int firstPitch, int secondPitch, String expected) {
        NormalFrame frame = (NormalFrame) Frame.createInitialFrame();
        frame.record(firstPitch);
        frame.record(secondPitch);

        assertThat(frame.getDescriptionForm()).isEqualTo(expected);
    }

    @Test
    @DisplayName("프레임이 종료되지 않은 상태일 때의 스코어 반환 요청 예외처리")
    void scoringWhenItCannotBeCalculated() {
        NormalFrame firstFrame = (NormalFrame) Frame.createInitialFrame();
        assertThatThrownBy(
                firstFrame::calculateScore
        ).isInstanceOf(InvalidScoreCalculationException.class);
    }

    @Test
    @DisplayName("Frame 완료(Miss, Spare, Strike) 시 새로운 Frame 반환")
    void createNextFrame() {
        Frame firstFrame = Frame.createInitialFrame();
        Frame currentFrame = firstFrame;

        currentFrame = currentFrame.record(4);
        assertThat(currentFrame).isEqualTo(firstFrame);
        currentFrame = currentFrame.record(3);
        assertThat(currentFrame).isEqualTo(firstFrame);
        currentFrame = currentFrame.record(3);
        assertThat(currentFrame).isNotEqualTo(firstFrame);
    }

    @Test
    @DisplayName("Frame에서 정적 메소드를 통해 1번 프레임을 생성하는 기능 테스트")
    void createFirstFrameWithFrameClass() {
        Frame firstFrame = Frame.createInitialFrame();

        assertThat(firstFrame).isInstanceOf(NormalFrame.class);
    }

    @Test
    @DisplayName("마지막 프레임에서 LastFrame을 생성하는 지 테스트")
    void testCreateLastFrameWithLastClass() {
        Frame ninthFrame = new NormalFrame(9);

        ninthFrame.record(4);
        ninthFrame.record(4);

        assertThat(ninthFrame.record(4))
                .isInstanceOf(LastFrame.class);
    }

    @Test
    @DisplayName("MISS 상태의 계산")
    void calculateScoreOnMiss() {
        NormalFrame frame = (NormalFrame) Frame.createInitialFrame();
        frame.record(3);
        frame.record(5);

        assertThat(frame.calculateScore())
                .isEqualTo(8);
    }

    @Test
    @DisplayName("Spare 상태의 계산")
    void calculateScoreMiss() {
        NormalFrame firstFrame = (NormalFrame) Frame.createInitialFrame();
        firstFrame.record(3);
        firstFrame.record(7);

        Frame secondFrame = firstFrame.record(8);

        assertThat(firstFrame.calculateScore())
                .isEqualTo(18);
    }

    @Test
    @DisplayName("Strike -> Miss나 Spare의 계산")
    void calculateScoreWhenStrikeWithMissSpare() {
        NormalFrame firstFrame = (NormalFrame) Frame.createInitialFrame();
        Frame secondFrame = firstFrame.record(10);

        secondFrame.record(4);
        secondFrame.record(4);

        assertThat(firstFrame.calculateScore())
                .isEqualTo(18);
    }

    @Test
    @DisplayName("Strike -> Strike -> Strike 의 스코어 계산")
    void calculateScoreWhenContinuousStrike() {
        NormalFrame firstFrame = (NormalFrame) Frame.createInitialFrame();

        Frame secondFrame = firstFrame.record(10);
        Frame thirdFrame = secondFrame.record(10);
        thirdFrame.record(10);

        assertThat(firstFrame.calculateScore())
                .isEqualTo(30);
    }
}
