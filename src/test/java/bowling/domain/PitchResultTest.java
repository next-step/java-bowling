package bowling.domain;

import bowling.exception.InvalidFallenPinsException;
import bowling.exception.InvalidSpareException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PitchResultTest {

    @DisplayName("쓰러뜨린 핀의 개수로 투구 결과를 생성한다.")
    @Test
    void create() {
        assertThat(PitchResult.of(5)).isEqualTo(PitchResult.of(5));
    }

    @DisplayName("쓰러뜨린 핀의 개수가 0 이상 10 이하여야 투구 결과를 생성할 수 있다.")
    @ValueSource(ints = {-1, 11})
    @ParameterizedTest
    void invalid(int fallenPins) {
        assertThatThrownBy(() -> PitchResult.of(fallenPins))
                .isInstanceOf(InvalidFallenPinsException.class);
    }

    @DisplayName("스페어 결과를 생성한다.")
    @Test
    void spare() {
        assertThat(PitchResult.spare(5)).isEqualTo(PitchResult.spare(5));
    }

    @DisplayName("스페어 결과 생성 시, 쓰러뜨린 핀의 개수는 0이 될 수 없다.")
    @Test
    void invalidSpare() {
        assertThatThrownBy(() -> PitchResult.spare(0))
                .isInstanceOf(InvalidSpareException.class);
    }

}
