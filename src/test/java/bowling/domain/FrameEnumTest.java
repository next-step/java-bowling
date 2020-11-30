package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class FrameEnumTest {

    @Test
    @DisplayName("1번 굴려서 10개를 쓰러트리면 STRIKE 이다.")
    void strike() {
        assertThat(FrameEnum.get(1, 10))
                .isEqualTo(FrameEnum.STRIKE);
    }

    @Test
    @DisplayName("2번 굴려서 10개를 쓰러트리면 SPARE 이다.")
    void spare() {
        assertThat(FrameEnum.get(2, 10))
                .isEqualTo(FrameEnum.SPARE);
    }

    @ParameterizedTest
    @DisplayName("2번 굴려서 10개를 쓰러트리지 못하면 MISS 이다.")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
    void miss(int countOfPins) {
        assertThat(FrameEnum.get(2, countOfPins))
                .isEqualTo(FrameEnum.MISS);
    }

    @ParameterizedTest
    @DisplayName("그 외에는 전부 UNFINISHED 이다.")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
    void unfinished(int countOfPins) {
        assertThat(FrameEnum.get(1, countOfPins))
                .isEqualTo(FrameEnum.UNFINISHED);
    }
}
