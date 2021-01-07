package bowling.domain.frame;

import bowling.bowlingexception.InvalidDownedPinNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class NormalFrameTest {

    @Test
    @DisplayName("2회 진행하였을 경우의 종료조건")
    void testIsEnd() {
        NormalFrame frame = new NormalFrame();
        int firstPitch = 4;
        int secondPitch = 3;

        assertThat(frame.isEnd()).isFalse();
        frame.record(firstPitch);
        assertThat(frame.isEnd()).isFalse();
        frame.record(secondPitch);
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("2회 진행시의 초과 범위 예외처리")
    void testInvalidRange() {
        NormalFrame frame = new NormalFrame();
        int firstPitch = 4;
        int secondPitch = 7;

        frame.record(firstPitch);

        assertThatThrownBy(() -> frame.record(secondPitch))
                .isInstanceOf(InvalidDownedPinNumberException.class);
    }

    @Test
    @DisplayName("10개를 첫 피치에 성공했을 때의 종료")
    void testIsStrike() {
        NormalFrame frame = new NormalFrame();

        assertThat(frame.isEnd()).isFalse();
        frame.record(10);
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("스트라이크 이후 추가적인 핀 갯수 등록")
    void testStrikesAdditionalScore() {
        NormalFrame frame = new NormalFrame();
        frame.record(10);

        if (frame.needAdditionalScore()) {
            frame.recordAdditionalScore(8);
        }
        if (frame.needAdditionalScore()) {
            frame.recordAdditionalScore(2);
        }

        assertThat(frame.calculateScore()).isEqualTo(20);
    }

    @Test
    @DisplayName("스페어 이후 추가적인 핀 등록")
    void testSparesAdditionalScore() {
        NormalFrame frame = new NormalFrame();
        frame.record(4);
        frame.record(6);

        if (frame.needAdditionalScore()) {
            frame.recordAdditionalScore(7);
        }

        assertThat(frame.calculateScore()).isEqualTo(17);
    }
}
