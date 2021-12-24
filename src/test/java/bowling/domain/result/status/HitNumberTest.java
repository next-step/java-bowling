package bowling.domain.result.status;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import bowling.domain.Pin;
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
    @DisplayName("HitNumber 생성시에는, Strike와 Gutter은 제외한다.")
    void validTest() {
        assertThatIllegalArgumentException().isThrownBy(() -> new HitNumber(Pin.of(10)));
        assertThatIllegalArgumentException().isThrownBy(() -> new HitNumber(Pin.of(0)));
    }


    @Test
    @DisplayName("Normal 표기가 잘 되는지 확인한다.")
    void getPrintMarkTest() {
        assertThat(hitNumber.getPrintMark()).isEqualTo("5");
    }
}