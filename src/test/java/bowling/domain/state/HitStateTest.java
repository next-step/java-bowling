package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class HitStateTest {

    @DisplayName("첫번째 시도에서 10개의 핀을 쓰러트리면 스트라이크를 반환해야 한다.")
    @Test
    void from_givenStrike() {
        assertThat(HitState.from(10, null)).isEqualTo(HitState.STRIKE);
    }

    @DisplayName("두번째 시도까지 10개의 핀을 쓰러트리면 스페어를 반환해야 한다.")
    @ParameterizedTest
    @CsvSource({
            "1,9",
            "2,8",
            "3,7",
            "4,6",
            "5,5",
            "6,4",
            "7,3",
            "8,2",
            "9,1",
    })
    void from_givenSpare(int hit, int previousHit) {
        assertThat(HitState.from(hit, previousHit)).isEqualTo(HitState.SPARE);
    }

    @DisplayName("두번째 시도까지 10개의 핀을 쓰러트리지 못하면 미스를 반환해야 한다.")
    @Test
    void from_givenMiss() {
        assertThat(HitState.from(1, 8)).isEqualTo(HitState.MISS);
    }

    @DisplayName("투구 기록이 0이며 거터를 반환해야 한다.")
    @Test
    void from_givenGutter() {
        assertThat(HitState.from(0, 0)).isEqualTo(HitState.GUTTER);
    }

}
