package bowling.model.delivery;

import bowling.model.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class FinalDeliveryEntryTest {

    private static Stream<Arguments> provideForBonusDelivery() {
        return Stream.of(
                Arguments.of(
                        10, 2, 8, Arrays.asList(Delivery.of(10), Delivery.of(2), new Delivery(8, State.SPARE))
                ),
                Arguments.of(
                        1, 9, 5, Arrays.asList(Delivery.of(1), new Delivery(9, State.SPARE), Delivery.of(5))
                ),
                Arguments.of(
                        0, 10, 5, Arrays.asList(Delivery.of(0), new Delivery(10, State.SPARE), Delivery.of(5))
                )

        );
    }

    @ParameterizedTest
    @MethodSource("provideForBonusDelivery")
    @DisplayName("roll : 보너스 투구까지 가능한 경우")
    void roll_with_bonus(int firstPins, int secondPins, int bonusPins, List<Delivery> expectedList) {
        // given
        FinalDeliveryEntry finalDeliveryEntry = new FinalDeliveryEntry(firstPins);
        finalDeliveryEntry.roll(secondPins);

        // when
        finalDeliveryEntry.roll(bonusPins);
        Stream<Delivery> result = finalDeliveryEntry.getDeliveries();

        // then
        assertThat(result.toArray()).containsExactly(expectedList.toArray());

    }

    private static Stream<Arguments> provideForIsEnd() {
        return Stream.of(
                Arguments.of(10, 2, Boolean.FALSE),
                Arguments.of(1, 9, Boolean.FALSE),
                Arguments.of(0, 9, Boolean.TRUE)
        );
    }

    @ParameterizedTest
    @MethodSource("provideForIsEnd")
    @DisplayName("두번째 투구 까지 했을 때 끝났는지 확인하기")
    void isEnd(int firstPins, int secondPins, boolean expected) {
        // given
        FinalDeliveryEntry finalDeliveryEntry = new FinalDeliveryEntry(firstPins);
        finalDeliveryEntry.roll(secondPins);

        // when
        boolean result = finalDeliveryEntry.isEnd();

        // then
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> provideForGetTotalFallenPins() {
        return Stream.of(
                Arguments.of(Arrays.asList(2, 8, 9), 19),
                Arguments.of(Arrays.asList(10, 10, 10), 30),
                Arguments.of(Arrays.asList(0, 10, 7), 17),
                Arguments.of(Arrays.asList(5, 4), 9)
        );
    }

    @ParameterizedTest
    @MethodSource("provideForGetTotalFallenPins")
    @DisplayName("넘어뜨린 총 핀수 구하기")
    void getTotalFallenPins(List<Integer> pins, int expected) {
        // given
        FinalDeliveryEntry finalDeliveryEntry = new FinalDeliveryEntry(pins.get(0));
        for (int i = 1; i < pins.size(); i++) {
            finalDeliveryEntry.roll(pins.get(i));
        }

        // when
        int result = finalDeliveryEntry.getTotalFallenPins();

        // then
        assertThat(result).isEqualTo(expected);
    }


}
