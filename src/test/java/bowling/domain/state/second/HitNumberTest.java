package bowling.domain.state.second;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.Pin;
import bowling.domain.state.end.first.HitNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HitNumberTest {

    private HitNumber hitNumber;

    @BeforeEach
    void init() {

        hitNumber = new HitNumber(Pin.of(5));
    }

    @Test
    @DisplayName("Normal 표기가 잘 되는지 확인한다.")
    void getPrintMarkTest() {
        assertThat(hitNumber.getPrintMark()).isEqualTo("5");
    }
}