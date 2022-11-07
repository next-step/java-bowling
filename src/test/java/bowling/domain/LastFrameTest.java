package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LastFrameTest {

    @Test
    void create() {
        Frame actual = new LastFrame(10, 10, 10);

        assertThat(actual).isEqualTo(new LastFrame(10, 10, 10));
    }

    @DisplayName("스트라이크가 아니고 첫 번째 점수와 두 번째 점수의 합이 10을 넘으면 IllegalArgumentException 예외를 throw 한다.")
    @Test
    void validate_sum() {
        assertThatThrownBy(() -> new LastFrame(9, 10, 10)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("첫 번째 프레임이 스트라이크가 아니고 두번째 프레임 결과가 스페어가 아니면 false를 반환한다.")
    @Test
    void is_remain_strike() {
        LastFrame firstStrikeFrame = new LastFrame();
        firstStrikeFrame.addScore(Score.of(8));
        firstStrikeFrame.addScore(Score.of(1));

        assertThatThrownBy(() -> firstStrikeFrame.addScore(Score.of(10))).isInstanceOf(IllegalArgumentException.class);
    }
}
