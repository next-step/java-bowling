package bowling.domain.state;

import bowling.domain.pin.DownedPins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static bowling.domain.Fixture.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("마지막 상태에서 여러 상태를 담기 위한 복합 상태")
class ComplexStateTest {

    @DisplayName("복합 상태를 초기화 하는데는 아무런 정보가 필요없다")
    @Test
    void init() {
        assertThat(ComplexState.init()).isInstanceOf(ComplexState.class);
    }

    @DisplayName("복합 상태에서 공을 굴리면 마지막 상태가 변경 된다")
    @Test
    void downPins() {
        ComplexState complexState = ComplexState.init();

        List<State> states = complexState.getState();
        assertThat(states.get(states.size() - 1)).isInstanceOf(Preparation.class);

        complexState.downPins(DOWNED_PINS_5);
        states = complexState.getState();
        assertThat(states.get(states.size() - 1)).isInstanceOf(InProgress.class);

        complexState.downPins(DOWNED_PINS_5);
        states = complexState.getState();
        assertThat(states.get(states.size() - 1)).isInstanceOf(Spare.class);
    }

    @DisplayName("복합 상태의 끝은 마지막 상태가 Miss일 경우 뿐이다")
    @MethodSource
    @ParameterizedTest
    void isEnd(List<DownedPins> prepareDownedPins, boolean expectedValue) {
        ComplexState complexState = ComplexState.init();
        prepareDownedPins.forEach(complexState::downPins);

        assertThat(complexState.isEnd()).isEqualTo(expectedValue);
    }

    private static Stream<Arguments> isEnd() {
        return Stream.of(
                Arguments.of(
                        Collections.singletonList(
                                DOWNED_PINS_10
                        ),
                        false
                ),

                Arguments.of(
                        Collections.singletonList(
                                DOWNED_PINS_5
                        ),
                        false
                ),

                Arguments.of(
                        Collections.emptyList(),
                        false
                ),

                Arguments.of(
                        Arrays.asList(
                                DOWNED_PINS_5,
                                DOWNED_PINS_5
                        ),
                        false
                ),

                Arguments.of(
                        Arrays.asList(
                                DOWNED_PINS_5,
                                DOWNED_PINS_2
                        ),
                        true
                )
        );
    }

    @DisplayName("복합 상태에서 마지막 상태가 Clean(Strike, Spare) 일 경우 추가적인 기회를 줄 수 있다")
    @MethodSource
    @ParameterizedTest
    void extraChance(List<DownedPins> prepareDownedPins) {
        ComplexState complexState = ComplexState.init();
        prepareDownedPins.forEach(complexState::downPins);

        int prevCount = complexState.getState().size();
        complexState.giveExtraChance();

        assertThat(complexState.getState().size()).isEqualTo(prevCount + 1);
    }

    private static Stream<Arguments> extraChance() {
        return Stream.of(
                Arguments.of( // strike
                        Collections.singletonList(
                                DOWNED_PINS_10
                        )
                ),

                Arguments.of( // spare
                        Arrays.asList(
                                DOWNED_PINS_5,
                                DOWNED_PINS_5
                        )
                )
        );
    }

}
