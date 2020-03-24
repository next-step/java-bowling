package bowling.domain.framestatus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class SpareTest {

    @Test
    @DisplayName("스페어 생성")
    void createSpare() {
        Spare spare = new Spare(0, 10);
        assertThat(spare.getPreScore()).isEqualTo(0);
        assertThat(spare.getCurrentScore()).isEqualTo(10);
    }

    @Test
    @DisplayName("스페어 생성시 예외 처리")
    void exceptSpare() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Spare(10, 0));
        assertThatIllegalArgumentException().isThrownBy(() -> new Spare(0, 0));
        assertThatIllegalArgumentException().isThrownBy(() -> new Spare(8, 7));
    }

    @Test
    @DisplayName("스페어 보너스 게임")
    void createSpareWithBonus() {
        Spare spare = new Spare(0, 10);
        assertThat(spare.isBonus()).isTrue();
    }
}
