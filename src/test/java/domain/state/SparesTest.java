package domain.state;

import domain.Pins;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class SparesTest {

    @Test
    @DisplayName("남아있는 핀 정보")
    void bowl() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Spares(Pins.of(5), Pins.of(4));
        });
    }

    @Test
    @DisplayName("더이상 추가 금지 예외처리")
    void bowlExcpetion() {
        Spares spares = new Spares(Pins.of(5), Pins.of(5));
        Assertions.assertThrows(RuntimeException.class, () -> {
            spares.bowl(Pins.of(4));
        });
    }

}