package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {

    @Test
    @DisplayName("일반적인 상황의 2회 입력 테스트")
    void testTwoTime() {
        NormalFrame frame = new NormalFrame();
        DownedPinPerTry firstTry = DownedPinPerTry.fromNumber(3);

        frame.record(firstTry);
        assertThat(frame.isEnd()).isFalse();

        frame.record(firstTry.fromFirstTry(DownedPinPerTry.fromNumber(6)));
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("첫 투구에 10개가 입력되었을 때 프레임 종료조건 테스트")
    void testStrike() {
        NormalFrame frame = new NormalFrame();
        DownedPinPerTry firstTry = DownedPinPerTry.fromNumber(10);

        frame.record(firstTry);

        assertThat(frame.isEnd()).isTrue();
    }
}
