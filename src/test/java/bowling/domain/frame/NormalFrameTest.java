package bowling.domain.frame;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class NormalFrameTest {

    @Test
    @DisplayName("스트라이크 는 공을 한번만 굴린다.")
    public void strikeBall() {
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.addPin(10);
        assertThat(normalFrame.getBallCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("스트라이크 가 아닌경우 공을 두번 굴린다.")
    public void normalBall() {
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.addPin(2);
        assertThat(normalFrame.getBallCount()).isEqualTo(2);
    }

    @Test
    @DisplayName("프레임 번호 에러발생")
    public void checkFrame() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new NormalFrame(11);

            fail("프레임 에러가 발생해야 한다.");
        });
    }

    @Test
    @DisplayName("핀 개수 에러발생")
    public void checkPinCount() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            NormalFrame normalFrame = new NormalFrame(1);
            normalFrame.addPin(9);
            normalFrame.addPin(10);

            fail("한 프레임의 핀개수 에러가 발생해야 한다.");
        });
    }

}
