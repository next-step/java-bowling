package bowling.domain.state.running;

import bowling.domain.Pins;
import bowling.domain.state.ThrowingState;
import bowling.domain.state.end.Strike;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class ReadyTest {

    @TestFactory
    Stream<DynamicTest> 투구_결과_상태_확인() {
        return Stream.of(
                dynamicTest("스트라이크", () -> {
                    ThrowingState ready = Ready.create();
                    assertThat(ready.bowl(Pins.create(10))).isInstanceOf(Strike.class);
                }),
                dynamicTest("FirstBowl", () -> {
                    ThrowingState ready = Ready.create();
                    assertThat(ready.bowl(Pins.create(3))).isInstanceOf(FirstBowl.class);
                })
        );
    }
}