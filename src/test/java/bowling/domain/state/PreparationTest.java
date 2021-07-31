package bowling.domain.state;

import bowling.domain.pin.DownedPin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("준비 상태를 표현하는 상태 클래스 테스트")
class PreparationTest {

    @DisplayName("준비 상태는 초기화시 필요한 정보가 없다")
    @Test
    void init() {
        assertThat(Preparation.instance()).isInstanceOf(Preparation.class);
    }

    @DisplayName("준비 상태에서 핀을 쓰러뜨릴면 다음 상태를 반환한다")
    @MethodSource
    @ParameterizedTest
    void downPins(DownedPin downedPin, State expectedState) {
        Preparation preparation = Preparation.instance();

        assertThat(preparation.downPins(downedPin)).isEqualTo(expectedState);
    }

    private static Stream<Arguments> downPins() {
        return Stream.of(
                Arguments.of(DownedPin.from(5), InProgress.from(DownedPin.from(5))),
                Arguments.of(DownedPin.from(10), Strike.instance())
        );
    }

}
