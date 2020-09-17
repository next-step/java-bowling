package bowling.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class DeliveryTest {

    private static Stream<Arguments> provideForNext() {
        return Stream.of(
                Arguments.of(8, 2, State.SPARE),
                Arguments.of(2, 0, State.GUTTER),
                Arguments.of(3, 5, State.MISS));
    }

    @ParameterizedTest
    @MethodSource("provideForNext")
    @DisplayName("다음 투구 진행하기")
    void next(int first, int second, State expectedState) {
        // given
        Delivery firstDelivery = Delivery.of(first);
        Delivery expected = new Delivery(second, expectedState);

        // when
        Delivery nextDelivery = firstDelivery.next(second);

        // then
        assertThat(nextDelivery).isEqualTo(expected);

    }

}
