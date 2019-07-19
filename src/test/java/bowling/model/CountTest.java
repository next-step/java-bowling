package bowling.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.model.Count.ZERO;
import static org.assertj.core.api.Assertions.assertThat;

class CountTest {

    @DisplayName("동등성 체크")
    @Test
    void createDefault() {
        // then
        assertThat(Count.of(ZERO)).isEqualTo(Count.of(ZERO));
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