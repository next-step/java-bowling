package bowling.domain.State;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LastFrameBowlTest {
    @DisplayName("보너스 볼이 없는데 호출하는 경우")
    @Test
    void validateLastFrameBowl() {
        LastFrameBowl lastFrameBowl = new LastFrameBowl(false);
        lastFrameBowl.bowl(2);
        lastFrameBowl.bowl(3);

        assertThatThrownBy(() -> {
            lastFrameBowl.bowl(4);
        }).isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("보너스볼이 있는 경우")
    @Test
    void bonusBowl() {
        LastFrameBowl lastFrameBowl = new LastFrameBowl(true);
        lastFrameBowl.bowl(2);
        lastFrameBowl.bowl(3);
        lastFrameBowl.bowl(4);
    }

    @DisplayName("마지막 프레임의 점수 표시를 확인한다.")
    @Test
    void desc() {
        LastFrameBowl lastFrameBowl = new LastFrameBowl(true);
        lastFrameBowl.bowl(10);
        lastFrameBowl.bowl(0);
        lastFrameBowl.bowl(4);
        String desc = lastFrameBowl.getDesc();
        assertThat(desc).isEqualTo("X|-|4");
    }
}
