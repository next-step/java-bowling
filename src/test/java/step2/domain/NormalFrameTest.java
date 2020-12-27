package step2.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    @Test
    @DisplayName("NormalFrame 초기화")
    void init() {
        assertThat(NormalFrame.init()).isInstanceOf(Frame.class);
    }

    @Test
    @DisplayName("투구하기")
    void bowl() {
        Pitch pitch = Pitch.from(8);
        Frame frame = NormalFrame.init();
        frame.bowl(pitch);

        assertThat(frame.getSize()).isEqualTo(1);
    }

    @Test
    @DisplayName("스트라이크이므로 종료 확인")
    void isFinishStrike() {
        Pitch pitch = Pitch.from(10);
        Frame frame = NormalFrame.init();
        frame.bowl(pitch);

        assertThat(frame.isFinish()).isTrue();
    }

    @Test
    @DisplayName("투구를 다 했으므로 종료 확인")
    void isFinishSize() {
        Pitch pitch1 = Pitch.from(3);
        Pitch pitch2 = Pitch.from(5);
        Frame frame = NormalFrame.init();
        frame.bowl(pitch1);
        frame.bowl(pitch2);

        assertThat(frame.isFinish()).isTrue();
    }

    @Test
    @DisplayName("종료가 아님")
    void isNotFinish() {
        Pitch pitch = Pitch.from(9);
        Frame frame = NormalFrame.init();
        frame.bowl(pitch);

        assertThat(frame.isFinish()).isFalse();
    }

    @Test
    @DisplayName("프레임의 점수가 10점을 넘을 경우 예외 처리")
    void exceptScore() {
        Pitch pitch = Pitch.from(6);
        Frame frame = NormalFrame.init();
        frame.bowl(pitch);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> frame.bowl(pitch));
    }

}