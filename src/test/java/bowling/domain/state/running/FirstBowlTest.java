package bowling.domain.state.running;

import bowling.domain.Pins;
import bowling.domain.state.ThrowingState;
import bowling.domain.state.end.Miss;
import bowling.domain.state.end.Spare;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class FirstBowlTest {

    @DisplayName("첫 번째 투구 후 남은 핀 보다 두 번째 투구에서 넘어트린 핀이 많을 경우")
    @Test
    void 예외_두_번째_투구() {
        int firstPinCount = 5;
        int secondPinCount = 6;

        ThrowingState state = FirstBowl.create(Pins.create(firstPinCount));
        assertThatThrownBy(() -> state.bowl(Pins.create(secondPinCount)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("넘어트린 핀의 총 합은 %d개보다 클 수 없습니다. 현재 %d개 입니다.", Pins.MAX_RANGE, firstPinCount + secondPinCount));
    }

    @TestFactory
    Stream<DynamicTest> 투구_결과_상태_확인() {
        return Stream.of(
                dynamicTest("스페어", () -> {
                    Pins firstPins = Pins.create(1);
                    Pins secondPins = Pins.create(9);
                    ThrowingState firstBowl = FirstBowl.create(firstPins);

                    assertThat(firstBowl.bowl(secondPins)).isEqualTo(Spare.create(firstPins, secondPins));
                }),
                dynamicTest("미스", () -> {
                    Pins firstPins = Pins.create(1);
                    Pins secondPins = Pins.create(2);
                    ThrowingState firstBowl = FirstBowl.create(firstPins);

                    assertThat(firstBowl.bowl(secondPins)).isEqualTo(Miss.create(firstPins, secondPins));
                })
        );
    }
}