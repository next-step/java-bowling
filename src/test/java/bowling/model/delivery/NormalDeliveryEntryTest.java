package bowling.model.delivery;

import bowling.ExceptionMessages;
import bowling.model.State;
import bowling.model.delivery.Delivery;
import bowling.model.delivery.NormalDeliveryEntry;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class NormalDeliveryEntryTest {

    private static Stream<Arguments> provideForRoll() {
        return Stream.of(
                Arguments.of(1, 9, Arrays.asList(Delivery.of(1), new Delivery(9, State.SPARE))),
                Arguments.of(0, 9, Arrays.asList(Delivery.of(0), new Delivery(9, State.MISS)))
        );
    }

    @ParameterizedTest
    @MethodSource("provideForRoll")
    @DisplayName("투구하기")
    void roll(int firstPins, int secondPins, List<Delivery> expectedDeliveries) {
        // given
        NormalDeliveryEntry normalDeliveryEntry = NormalDeliveryEntry.of(firstPins);

        // when
        normalDeliveryEntry.roll(secondPins);

        // then
        assertThat(normalDeliveryEntry.getDeliveries().toArray()).containsExactly(expectedDeliveries.toArray());

    }

    @Test
    @DisplayName("투구하기 실패 : 처음에 strike를 쳤을 때")
    void roll_fail() {
        // given
        NormalDeliveryEntry normalDeliveryEntry = NormalDeliveryEntry.of(10);

        // when
        assertThatIllegalStateException()
                .isThrownBy(() -> { normalDeliveryEntry.roll(3); })
                .withMessage(ExceptionMessages.NO_LEFT_DELIVERY);
    }


    private static Stream<Arguments> provideForIsEnd() {
        return Stream.of(
                Arguments.of(NormalDeliveryEntry.of(3)),
                Arguments.of(NormalDeliveryEntry.of(5))
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 7, 9})
    @DisplayName("isEnd : 투구가 끝나지 않은 경우")
    void isEnd_false(int firstPins) {
        // given
        NormalDeliveryEntry normalDeliveryEntry = NormalDeliveryEntry.of(firstPins);

        // when
        boolean isEnd = normalDeliveryEntry.isEnd();

        // then
        assertThat(isEnd).isFalse();
    }

    private List<NormalDeliveryEntry> getEndedEntryList() {
        List<NormalDeliveryEntry> normalDeliveryEntries = new ArrayList<>();
        NormalDeliveryEntry strike = NormalDeliveryEntry.of(10);
        NormalDeliveryEntry spare = NormalDeliveryEntry.of(2);
        NormalDeliveryEntry miss = NormalDeliveryEntry.of(5);

        spare.roll(8);
        miss.roll(3);

        normalDeliveryEntries.add(strike);
        normalDeliveryEntries.add(spare);
        normalDeliveryEntries.add(miss);

        return normalDeliveryEntries;
    }

    @Test
    @DisplayName("isEnd : 투구가 모두 끝난 경우")
    void isEnd_true() {
        // given
        List<NormalDeliveryEntry> endedEntryList = getEndedEntryList();

        // when
        endedEntryList.forEach(entry -> {
            assertThat(entry.isEnd()).isTrue();
        });
    }

    @Test
    @DisplayName("엔트리의 상태 확인하기 : strike인 경우")
    void getState_when_strike() {
        // given
        NormalDeliveryEntry normalDeliveryEntry = NormalDeliveryEntry.of(10);

        // when
        State result = normalDeliveryEntry.getState();

        // then
        assertThat(result).isEqualTo(State.STRIKE);
    }

    private static Stream<Arguments> provideForGetState() {
        return Stream.of(
                Arguments.of(1, 9, State.SPARE),
                Arguments.of(3, 6, State.MISS),
                Arguments.of(2, 3, State.MISS)
        );
    }

    @ParameterizedTest
    @MethodSource("provideForGetState")
    @DisplayName("엔트리의 상태 확인하기")
    void getState(int firstPins, int secondPins, State expected) {
        // given
        NormalDeliveryEntry normalDeliveryEntry = NormalDeliveryEntry.of(firstPins);
        normalDeliveryEntry.roll(secondPins);

        // when
        State result = normalDeliveryEntry.getState();

        // then
        assertThat(result).isEqualTo(expected);

    }

    private static Stream<Arguments> provideForGetTotalFallenPins() {
        return Stream.of(
                Arguments.of(Arrays.asList(2, 8), 10),
                Arguments.of(Arrays.asList(10), 10),
                Arguments.of(Arrays.asList(2, 7), 9)
        );
    }

    @ParameterizedTest
    @MethodSource("provideForGetTotalFallenPins")
    @DisplayName("넘어뜨린 총 핀수 구하기")
    void getTotalFallenPins(List<Integer> pins, int expected) {
        // given
        NormalDeliveryEntry normalDeliveryEntry = NormalDeliveryEntry.of(pins.get(0));
        for (int i = 1; i < pins.size(); i++) {
            normalDeliveryEntry.roll(pins.get(i));
        }

        // when
        int result = normalDeliveryEntry.getTotalFallenPins();

        // then
        assertThat(result).isEqualTo(expected);
    }

}
