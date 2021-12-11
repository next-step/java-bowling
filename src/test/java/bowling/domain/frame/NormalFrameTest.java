package bowling.domain.frame;

import bowling.domain.pin.Pin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    Frame firstFrame;

    @BeforeEach
    void initFrame() {
        firstFrame = NormalFrame.first();
    }

    @DisplayName("첫 번째 프레임은 1프레임이다.")
    @Test
    void firstFrame() {
        assertThat(firstFrame.getNumber()).isEqualTo(1);
    }

    @DisplayName("8프레임 까지는 NormalFrame, 9프레임은 FinalFrame")
    @Test
    void next() {
        Frame frame = firstFrame;
        for (int number = 2; number <= 9; number++) {
            frame = frame.next();

            assertThat(frame.getNumber()).isEqualTo(number);
            assertThat(frame).isInstanceOf(NormalFrame.class);
        }
        frame = frame.next();
        assertThat(frame).isInstanceOf(FinalFrame.class);
    }

    @DisplayName("스트라이크인 경우 이번 프레임에 더 투구할 수 없다.")
    @Test
    void pitch_strike() {
        Pin allHitPin = pin(10);
        assertThat(firstFrame.pitch(allHitPin)).isFalse();
    }

    @DisplayName("첫번째가 스트라이크가 아니라면 총 두번 투구할 수 있다.")
    @ParameterizedTest(name = "[{index}] firstHitCount: {0}, secondHitCount: {1}")
    @CsvSource({
            "1, 9",
            "0, 0",
            "5, 4",
            "2, 2",
    })
    void pitch_notStrike(int firstHitCount, int secondHitCount) {
        Pin firstPin = pin(firstHitCount);
        assertThat(firstFrame.pitch(firstPin)).isTrue();

        Pin secondPin = pin(secondHitCount);
        assertThat(firstFrame.pitch(secondPin)).isFalse();
    }

    private Pin pin(int hitCount) {
        return Pin.from(hitCount);
    }
}
