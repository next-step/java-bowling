package step2.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest {

    @Test
    @DisplayName("FinalFrame 초기화")
    void init() {
        assertThat(FinalFrame.init()).isInstanceOf(Frame.class);
    }

    @Test
    @DisplayName("투구하기")
    void bowl() {
        Pitch pitch = Pitch.from(8);
        Frame frame = FinalFrame.init();
        frame.bowl(pitch);

        assertThat(frame.getSize()).isEqualTo(1);
    }

    @Test
    @DisplayName("스트라이크일 때, 3회 종료 확인")
    void isFinishStrike() {
        Pitch pitch = Pitch.from(10);
        Frame frame = FinalFrame.init();
        frame.bowl(pitch);
        frame.bowl(pitch);
        frame.bowl(pitch);

        assertThat(frame.isFinish()).isTrue();
    }

    @Test
    @DisplayName("스페어일 때, 3회 종료 확인")
    void isFinishSpare() {
        Pitch pitch1 = Pitch.from(8);
        Pitch pitch2 = Pitch.from(2);
        Pitch pitch3 = Pitch.from(10);
        Frame frame = FinalFrame.init();
        frame.bowl(pitch1);
        frame.bowl(pitch2);
        frame.bowl(pitch3);

        assertThat(frame.isFinish()).isTrue();
    }

    @Test
    @DisplayName("미스일 때, 2회 종료 확인")
    void isFinishMiss() {
        Pitch pitch1 = Pitch.from(8);
        Pitch pitch2 = Pitch.from(1);
        Frame frame = FinalFrame.init();
        frame.bowl(pitch1);
        frame.bowl(pitch2);

        assertThat(frame.isFinish()).isTrue();
    }

    @Test
    @DisplayName("종료가 아님")
    void isNotFinish() {
        Pitch pitch = Pitch.from(9);
        Frame frame = FinalFrame.init();
        frame.bowl(pitch);

        assertThat(frame.isFinish()).isFalse();
    }

    @Test
    @DisplayName("첫투구가 스트라이크가 아닌데 점수의 합이 10을 넘길 경우 예외 처리")
    void exceptScore() {
        Pitch pitch = Pitch.from(6);
        Frame frame = FinalFrame.init();
        frame.bowl(pitch);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> frame.bowl(pitch));
    }
}