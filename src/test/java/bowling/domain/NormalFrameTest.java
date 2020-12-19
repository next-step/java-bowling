package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {

    @Test
    @DisplayName("일반적인 상황의 2회 입력 테스트")
    void testTwoTime() {
        NormalFrame frame = new NormalFrame();
        DownedPin firstTry = DownedPin.fromNumber(3);

        frame.record(firstTry);
        assertThat(frame.isEnd()).isFalse();

        frame.record(firstTry.fromSubordinateTry(DownedPin.fromNumber(6)));
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("첫 투구에 10개가 입력되었을 때 프레임 종료조건 테스트")
    void endedWhenStrike() {
        NormalFrame frame = new NormalFrame();
        DownedPin firstTry = DownedPin.fromNumber(10);

        frame.record(firstTry);

        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("스트라이크 컨디션 테스트(true)")
    void isStrike() {
        NormalFrame frame = new NormalFrame();
        DownedPin firstTry = DownedPin.fromNumber(10);

        frame.record(firstTry);

        assertThat(firstTry.isStrike()).isTrue();
    }

    @Test
    @DisplayName("스트라이크 컨디션 테스트(false)")
    void isNotStrike() {
        NormalFrame frame = new NormalFrame();
        DownedPin firstTry = DownedPin.fromNumber(7);

        frame.record(firstTry);

        assertThat(firstTry.isStrike()).isFalse();
    }

    @Test
    @DisplayName("스페어 컨디션 테스트(true)")
    void isSpare() {
        NormalFrame frame = new NormalFrame();
        DownedPin firstTry = DownedPin.fromNumber(3);
        DownedPin secondTry = firstTry.fromSubordinateTry(DownedPin.fromNumber(7));

        frame.record(firstTry);
        frame.record(secondTry);

        assertThat(frame.isSpare()).isTrue();
    }
}
