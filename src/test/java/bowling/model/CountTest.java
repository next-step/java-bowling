package bowling.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CountTest {

    private static final int STRIKE_BONUS_COUNT = 2;

    @DisplayName("스트라이크 일 시 " + STRIKE_BONUS_COUNT + "번의 보너스 점수를 획득한다")
    @Test
    void createDefault() {
        // when
        Count count = Count.of(STRIKE_BONUS_COUNT);

        // then
        assertThat(Count.of(STRIKE_BONUS_COUNT)).isEqualTo(Count.of(STRIKE_BONUS_COUNT));
    }

    @DisplayName("카운트가 감소한다")
    @Test
    void decrease_success() {
        // when
        Count count = Count.of(1);
        Count result = count.decrease();

        // then
        assertThat(result.getCount()).isEqualTo(0);
    }

    @DisplayName("카운트를 증가한다")
    @Test
    void increase_success() {
        // when
        Count count = Count.of(1);
        Count result = count.increase();

        // then
        assertThat(result.isMatch(Count.of(2))).isTrue();
    }
}