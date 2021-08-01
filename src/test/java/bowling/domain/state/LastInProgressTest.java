package bowling.domain.state;

import bowling.domain.pin.DownedPins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static bowling.domain.Fixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("마지막 프레임의 진행상태 테스트")
class LastInProgressTest {

    @DisplayName("마지막 프레임 진행 상태는 초기화시 아무것도 필요하지 않다")
    @Test
    void init() {
        assertThat(LastInProgress.init()).isInstanceOf(LastInProgress.class);
    }

    @DisplayName("마지막 프레임의 진행 상태는 최대 3번까지 투구가 가능하다")
    @MethodSource
    @ParameterizedTest
    void downPins(List<DownedPins> prepareDownedPins) {
        LastInProgress lastInProgress = LastInProgress.init();

        prepareDownedPins.forEach(lastInProgress::downPins);

        assertThat(lastInProgress.isEnd()).isTrue();
    }

    private static Stream<Arguments> downPins() {
        return Stream.of(
                Arguments.of( // Miss
                        Arrays.asList(
                            DOWNED_PINS_5,
                            DOWNED_PINS_2
                        )
                ),
                Arguments.of( // Spare, roll
                        Arrays.asList(
                            DOWNED_PINS_5,
                            DOWNED_PINS_5,
                            DOWNED_PINS_5
                        )
                ),
                Arguments.of( // Strike, Miss
                        Arrays.asList(
                                DOWNED_PINS_10,
                                DOWNED_PINS_5,
                                DOWNED_PINS_2
                        )
                ),
                Arguments.of( // Strike, Spare
                        Arrays.asList(
                                DOWNED_PINS_10,
                                DOWNED_PINS_5,
                                DOWNED_PINS_5
                        )
                ),
                Arguments.of( // Double Strike, roll
                        Arrays.asList(
                                DOWNED_PINS_10,
                                DOWNED_PINS_10,
                                DOWNED_PINS_5
                        )
                ),
                Arguments.of( // Triple Strike
                        Arrays.asList(
                                DOWNED_PINS_10,
                                DOWNED_PINS_10,
                                DOWNED_PINS_10
                        )
                )
        );
    }

    @DisplayName("마지막 프레임의 진행 상태는 최대 3번까지 가능하지만, 불가능한 경우 예외를 발생 시킨다")
    @MethodSource
    @ParameterizedTest
    void downPinsException(List<DownedPins> prepareDownedPins) {
        LastInProgress lastInProgress = LastInProgress.init();

        assertThatThrownBy(() -> prepareDownedPins.forEach(lastInProgress::downPins)).isInstanceOf(IllegalStateException.class);
    }

    private static Stream<Arguments> downPinsException() {
        return Stream.of(
                Arguments.of( // Miss
                        Arrays.asList(
                                DOWNED_PINS_5,
                                DOWNED_PINS_2,
                                DOWNED_PINS_2
                        )
                ),
                Arguments.of( // Triple Strike
                        Arrays.asList(
                                DOWNED_PINS_10,
                                DOWNED_PINS_10,
                                DOWNED_PINS_10,
                                DOWNED_PINS_10
                        )
                )
        );
    }
}
