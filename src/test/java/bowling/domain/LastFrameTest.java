package bowling.domain;

import bowling.bowlingexception.InvalidDownedPinNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class LastFrameTest {

    @Test
    @DisplayName("스페어나 스트라이크가 아닐 때의 종료 조건")
    void testOnNormal() {
        LastFrame lastFrame = new LastFrame();
        lastFrame.record(DownedPinPerTry.fromNumber(3));
        lastFrame.record(DownedPinPerTry.fromNumber(6));

        assertThat(lastFrame.isEnded()).isTrue();
    }

    @Test
    @DisplayName("Spare 의 종료 조건")
    void testRecordOnSpare() {
        LastFrame lastFrame = new LastFrame();
        lastFrame.record(DownedPinPerTry.fromNumber(3));
        lastFrame.record(DownedPinPerTry.fromNumber(7));

        lastFrame.record(DownedPinPerTry.fromNumber(8));

        assertThat(lastFrame.isEnded()).isTrue();
    }

    @Test
    @DisplayName("Strike 의 종료 조건")
    void testRecordOnStrike() {
        LastFrame lastFrame = new LastFrame();
        lastFrame.record(DownedPinPerTry.fromNumber(10));
        lastFrame.record(DownedPinPerTry.fromNumber(7));
        lastFrame.record(DownedPinPerTry.fromNumber(3));

        assertThat(lastFrame.isEnded()).isTrue();
    }

    @Test
    @DisplayName("Strike 이후 예외처리")
    void testInvalidInputOnStrike() {
        LastFrame lastFrame = new LastFrame();
        lastFrame.record(DownedPinPerTry.fromNumber(10));
        lastFrame.record(DownedPinPerTry.fromNumber(7));

        assertThatThrownBy(
                () -> lastFrame.record(DownedPinPerTry.fromNumber(4))
        ).isInstanceOf(InvalidDownedPinNumberException.class);

    }
}
