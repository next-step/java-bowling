package bowling.domain.state;

import bowling.domain.pin.DownedPins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static bowling.domain.Fixture.DOWNED_PINS_10;
import static bowling.domain.Fixture.DOWNED_PINS_5;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("준비 상태를 표현하는 상태 클래스 테스트")
class PreparationTest {

    @DisplayName("준비 상태는 초기화시 필요한 정보가 없다")
    @Test
    void init() {
        assertThat(Preparation.init()).isInstanceOf(Preparation.class);
    }

    @DisplayName("준비 상태에서 핀을 쓰러뜨리면 다음 상태를 반환한다")
    @MethodSource
    @ParameterizedTest
    void downPins(DownedPins downedPins, Class<State> expectedState) {
        Preparation preparation = Preparation.init();

        assertThat(preparation.downPins(downedPins)).isInstanceOf(expectedState);
    }

    private static Stream<Arguments> downPins() {
        return Stream.of(
                Arguments.of(DOWNED_PINS_5, InProgress.class),
                Arguments.of(DOWNED_PINS_10, Strike.class)
        );
    }

    @DisplayName("플레이어가 던진 후의 최신 프레임에서 상태값이 Preparation 이라면 플레이 순서를 교대해야하는 turn over 상태이다")
    @Test
    void turnOver() {
        Preparation preparation = Preparation.init();

        assertThat(preparation.isTurnOver()).isTrue();
    }

}
