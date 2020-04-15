package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RollsTest {

    @DisplayName("공은 2번만 굴릴수 있다 그이상일 경우 예외처리")
    @Test
    void validateRolls() {
        Rolls rolls = new Rolls();
        rolls.add(Pin.of(1));
        rolls.add(Pin.of(2));
        assertThatIllegalArgumentException().isThrownBy(() -> {
            rolls.add(Pin.of(3));
        });

    }

    @DisplayName("공을 투구한 이력을 추가한다.")
    @Test
    void addRoll() {
        Rolls rolls = new Rolls();
        rolls.add(Pin.of(8));
        assertThat(rolls.getRolls()).hasSize(1);
    }

    @DisplayName("공을 세번 굴릴 수 있을때 보너스 투구 추가시")
    @Test
    void addBonusRoll() {
        Rolls rolls = new Rolls();
        rolls.add(Pin.of(1));
        rolls.add(Pin.of(2));
        rolls.addBonusRoll();
        rolls.add(Pin.of(10));
        assertThat(rolls.getRolls()).hasSize(3);
    }
}
