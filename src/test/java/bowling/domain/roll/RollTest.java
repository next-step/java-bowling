package bowling.domain.roll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class RollTest {

    @ParameterizedTest
    @ValueSource(ints = { -1, 15 })
    @DisplayName("쓰러뜨린 핀 개수가 0~10 범위가 아닌 경우 예외")
    void testCountOfKnockedPins(int countOfPins) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Roll(RollType.STRIKE, countOfPins));
    }

    @Test
    @DisplayName("주어진 롤 타입 확인")
    void testRollType() {
        assertThat(new Roll(RollType.STRIKE, 0).isStrike()).isTrue();
        assertThat(new Roll(RollType.SPARE, 0).isSpare()).isTrue();
        assertThat(new Roll(RollType.DEFAULT, 0).isSpare()).isFalse();
        assertThat(new Roll(RollType.GUTTER, 0).isSpare()).isFalse();
    }

    @Test
    @DisplayName("정상 생성")
    void testRollCreate() {
        int knockedPins = 10;
        assertThat(new Roll(RollType.DEFAULT, knockedPins).getCountOfKnockedPins())
                .isEqualTo(knockedPins);
    }
}
