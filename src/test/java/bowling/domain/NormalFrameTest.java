package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NormalFrameTest {

    @DisplayName("프레임 넘버가 유효한지 체크한다.")
    @Test
    void validateFrameNum() {
        assertAll(() -> assertThatIllegalArgumentException().isThrownBy(() -> {
                      new NormalFrame(0);
                  }),
                  () -> assertThatIllegalArgumentException().isThrownBy(() -> {
                      new NormalFrame(11);
                  })
        );
    }

    @DisplayName("프레임에 공을 한번 투구한다.")
    @Test
    void bowl() {
        NormalFrame frame = new NormalFrame(1);
        frame.bowl(8);
        Pin pin = frame.getPin();
        assertThat(pin).isEqualTo(new Pin(8));
    }

    @DisplayName("프레임에 공을 두번 투구한다.")
    @Test
    void bowl2() {
        NormalFrame frame = new NormalFrame(1);
        frame.bowl(8);
        frame.bowl(2);
        Pin pin = frame.getPin();
        assertThat(pin).isEqualTo(new Pin(10));
    }

    @DisplayName("다음 프레임 생성시 NormalFrame 을 생성 할 경우")
    @Test
    void createNextNomarFrame() {
        NormalFrame frame = new NormalFrame(2);
        NormalFrame nextFrame = frame.createNextFrame(true);
        assertThat(nextFrame).isEqualTo(new NormalFrame(3));
    }

    @DisplayName("다음 프레임 생성시 FinalFrame 을 생성 할 경우")
    @Test
    void createNextFinalFrame() {
        NormalFrame frame = new NormalFrame(9);
        NormalFrame nextFrame = frame.createNextFrame(true);
        assertThat(nextFrame).isEqualTo(new FinalFrame(10, true));
    }
}
