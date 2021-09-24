package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InningTest {

    @Test
    @DisplayName("inning 값이 1인 이닝을 생성한다.")
    void init() {
        Inning first = Inning.first();
        assertThat(first).isEqualTo(Inning.first());
    }

    @Test
    @DisplayName("다음 이닝의 값을 가진 이닝을 생성한다.")
    void next_inning() {
        Inning inning = Inning.from(5);
        Inning nextInning = inning.nextInning();

        assertThat(nextInning).isEqualTo(Inning.from(6));
    }

}