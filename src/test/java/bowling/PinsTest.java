package bowling;

import bowling.domain.Pins;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class PinsTest {

    @Test
    void 핀_생성() {
        assertThat(Pins.create(1)).isNotNull();
    }

    @TestFactory
    Stream<DynamicTest> 쓰러진_핀_결과() {
        return Stream.of(
                dynamicTest("스트라이크", () -> assertThat(Pins.create(10).isStrike()).isTrue()),
                dynamicTest("스페어", () -> assertThat(Pins.create(9).isSpare(Pins.create(1))).isTrue()),
                dynamicTest("미스", () -> assertThat(Pins.create(8).isMiss(Pins.create(1))).isTrue()),
                dynamicTest("거터", () -> assertThat(Pins.create(0).isGutter()).isTrue())
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void 예외_쓰러진_핀_개수_범위(int pinCount) {
        assertThatThrownBy(() -> Pins.create(pinCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("쓰러질 수 있는 핀의 개수는 %d개 이상 %d개 이하 입니다. 현재 쓰러트린 개수는 %d개 입니다.", Pins.MIN_RANGE, Pins.MAX_RANGE, pinCount));
    }
}