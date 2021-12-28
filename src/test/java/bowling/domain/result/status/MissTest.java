package bowling.domain.result.status;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import bowling.domain.Pin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MissTest {

    private Miss miss;

    @BeforeEach
    void init() {

        miss = new Miss(Pin.of(0));
    }

    @Test
    @DisplayName("Miss는 핀 갯수가 0개이어야 한다.")
    void validTest() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Miss(Pin.of(4)));
    }

    @Test
    @DisplayName("Miss 표기가 잘 되는지 확인한다.")
    void getPrintMarkTest() {
        assertThat(miss.getPrintMark()).isEqualTo("-");
    }

}