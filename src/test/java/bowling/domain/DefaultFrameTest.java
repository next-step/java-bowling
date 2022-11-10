package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DefaultFrameTest {

    @Test
    void create() {
        Frame actual = new DefaultFrame();

        assertThat(actual).isEqualTo(new DefaultFrame());
    }

    @DisplayName("첫 번째 점수와 두 번째 점수의 합이 10을 넘으면 IllegalArgumentException 예외를 throw 한다.")
    @Test
    void validate_sum() {
        DefaultFrame defaultFrame = new DefaultFrame();
        defaultFrame.addScore(Score.of(5));

        assertThatThrownBy(() -> defaultFrame.addScore(Score.of(6))).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("첫번째 시도가 스트라이크면 false를 반환한다.")
    @Test
    void is_not_remain_chance_strike() {
        Frame strikeFrame = new DefaultFrame();
        strikeFrame.addScore(Score.of(10));

        assertThat(strikeFrame.isRemainChance()).isFalse();
    }

    @DisplayName("첫번째 시도가 스트라이크가 아니고 두 점수의 합이 10을 넘으면 IllegalArgumentException 예외를 throw 한다.")
    @Test
    void is_not_remain_chance_size() {
        Frame strikeFrame = new DefaultFrame();
        strikeFrame.addScore(Score.of(9));
        assertThatThrownBy(() -> strikeFrame.addScore(Score.of(2))).isInstanceOf(IllegalArgumentException.class);
    }
}
