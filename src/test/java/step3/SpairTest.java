package step3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step3.domain.Pins;
import step3.state.Spair;

public class SpairTest {
    @DisplayName("Spair 상태에서는 추가적으로 공을 던질 수 없습니다.")
    @Test
    void spairBowlTest() {
        Spair spair = new Spair(new Pins(1), new Pins(9));
        Assertions.assertThatThrownBy(() -> {
            spair.bowl(4);
        }).isInstanceOf(IllegalArgumentException.class);
    }

}