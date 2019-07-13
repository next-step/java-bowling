package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-13 17:41
 */
public class NormalFrameTest {
    @DisplayName("현재 몇번째 Frame인지 확인")
    @Test
    void getIndex() {
        NormalFrame originFrame = new NormalFrame();

        Frame bowl1 = originFrame.bowl(10);
        Frame bowl2 = bowl1.bowl(3);
        Frame bowl3 = bowl2.bowl(2);

        assertAll(
                () -> assertThat(bowl1.getIndex()).isEqualTo(1),
                () -> assertThat(bowl2.getIndex()).isEqualTo(2),
                () -> assertThat(bowl3.getIndex()).isEqualTo(2),
                () -> assertThat(bowl3.bowl(10).getIndex()).isEqualTo(3)
        );
    }

    @DisplayName("Frame 종료 후 새로운 Frame 생성되는지 확인")
    @Test
    void new_confirm_Frame() {
        NormalFrame originFrame = new NormalFrame();
        originFrame.bowl(10);
        assertThat(originFrame.bowl(10)).isNotSameAs(originFrame);
    }
}
