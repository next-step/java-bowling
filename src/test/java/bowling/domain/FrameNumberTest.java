package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-17 00:30
 */
public class FrameNumberTest {
    @DisplayName("전달 받은 번호를 통해 생성 - 11번째 프레임은 생성 불가")
    @Test
    void 열한번째_프레임_생성_불가() {
        FrameNumber number = FrameNumber.init();
        for (int i = 0; i < 9; i++) {
            number = number.next();
        }
        FrameNumber finalNumber = number;
        assertThatExceptionOfType(IllegalStateException.class).isThrownBy(() -> {
            finalNumber.next();
        }).withMessageContaining("10프레임까지만 게임할 수 있습니다.");
    }

    @DisplayName("게임이 종료되었는지 (10번째 프레임인지) 확인")
    @Test
    void 게임_종료() {
        FrameNumber number = FrameNumber.init();
        for (int i = 0; i < 9; i++) {
            number = number.next();
        }
        assertThat(number.isOver()).isTrue();
    }

    @DisplayName("NormalFrame 종료되었는지 (9번째 프레임인지) 확인")
    @Test
    void 일반프레임_종료() {
        FrameNumber number = FrameNumber.init();
        for (int i = 0; i < 8; i++) {
            number = number.next();
        }
        assertThat(number.isNormalFrameOver()).isTrue();
    }
}
