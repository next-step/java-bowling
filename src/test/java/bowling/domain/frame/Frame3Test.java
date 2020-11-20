package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Frame3Test {

    @Test
    @DisplayName("spare는 세번의 라운드를 진행할수있다.")
    void spare() {
        Frame3 finalFrame = Frame3.first().last();
        finalFrame.roll(1);
        finalFrame.roll(9);
        finalFrame.roll(9);

        assertThat(finalFrame.canRoll()).isEqualTo(false);
        assertThat(finalFrame.getScore()).isEqualTo(19);
    }
}
