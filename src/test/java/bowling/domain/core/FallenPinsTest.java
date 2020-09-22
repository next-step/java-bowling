package bowling.domain.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class FallenPinsTest {
    @DisplayName("단일객체만 생성하는 지 확인")
    @Test
    void singleton() {
        FallenPins fallenPins = FallenPins.of(0);
        FallenPins fallenPins2 = FallenPins.of(0);
        assertThat(fallenPins == fallenPins2).isTrue();
        assertThat(fallenPins.equals(fallenPins2)).isTrue();
    }

    @DisplayName("잘못 설정된 넘어진 핀 개수를 설정하면 오류 발생 유무 확인")
    @ParameterizedTest
    @ValueSource(ints = {-1,11})
    void wrongFellenPinsCount(final int wrongPins) {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> FallenPins.of(wrongPins))
            .withMessage(FallenPins.ERROR_MESSAGE);
    }
}
