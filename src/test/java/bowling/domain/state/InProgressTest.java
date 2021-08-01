package bowling.domain.state;

import bowling.domain.pin.DownedPins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static bowling.domain.Fixture.DOWNED_PINS_2;
import static bowling.domain.Fixture.DOWNED_PINS_5;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("진행중 상태의 테스트")
class InProgressTest {

    @DisplayName("진행중 상태는 하나의 쓰러진 핀을 가지고 초기화 한다")
    @Test
    void init() {
        assertThat(InProgress.from(DOWNED_PINS_5)).isInstanceOf(InProgress.class);
    }

    @DisplayName("진행중 상태에서 핀을 쓰러뜨리면 다음 상태를 반환한다")
    @MethodSource
    @ParameterizedTest
    void downPins(DownedPins downedPins, Class<State> expectedState) {
        InProgress inProgress = InProgress.from(DOWNED_PINS_5);

        assertThat(inProgress.downPins(downedPins)).isInstanceOf(expectedState);
    }

    private static Stream<Arguments> downPins() {
        return Stream.of(
                Arguments.of(DOWNED_PINS_5, Spare.class),
                Arguments.of(DOWNED_PINS_2, Miss.class)
        );
    }

}
