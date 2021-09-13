package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class InningTest {

    @Test
    @DisplayName("inning 값이 1인 이닝을 생성한다.")
    void init() {
        Inning first = Inning.first();
        assertThat(first).isEqualTo(new Inning(1));
    }

    @Test
    @DisplayName("다음 이닝의 값을 가진 이닝을 생성한다.")
    void next_inning() {
        Inning inning = new Inning(5);
        Inning nextInning = inning.nextInning();

        assertThat(nextInning).isEqualTo(new Inning(6));
    }

}