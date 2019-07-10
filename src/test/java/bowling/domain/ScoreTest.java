package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-10 16:00
 */
public class ScoreTest {
    @DisplayName("점수 생성 시 0 ~ 10 자연수만 가능 - 예외상황")
    @Test
    void createScore_Excpetion() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            Score score = Score.of(11);
        }).withMessageContaining("0 ~ 10 사이만 가능합니다.");
    }
}
