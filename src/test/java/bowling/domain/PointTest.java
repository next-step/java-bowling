package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-16 23:04
 */
public class PointTest {
    @DisplayName("포인트 생성 - 11입력 시 생성 불가")
    @Test
    void 포인트_생성_예외상황() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            Point.of(11);
        }).withMessageContaining("점수는 10 이하의 자연수만 가능합니다.");
    }
}
