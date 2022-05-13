package bowling.domain.util;

import bowling.domain.pin.PinNo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PinNoPrinterTest {

    @ParameterizedTest
    @MethodSource("provideArgumentsForPrintList")
    void printList(List<PinNo> pinNos, String result) {
        assertThat(PinNoPrinter.print(pinNos)).isEqualTo(result);
    }

    private static Stream<Arguments> provideArgumentsForPrintList() {
        return Stream.of(
                Arguments.of(List.of(PinNo.of(1)), "1"),
                Arguments.of(List.of(PinNo.of(10)), "X"),
                Arguments.of(List.of(PinNo.of(1), PinNo.of(9)), "1|/"),
                Arguments.of(List.of(PinNo.of(1), PinNo.of(8)), "1|8"),
                Arguments.of(List.of(PinNo.of(1), PinNo.of(9), PinNo.of(10)), "1|/|X"),
                Arguments.of(List.of(PinNo.of(10), PinNo.of(0), PinNo.of(3)), "X|-|3"),
                Arguments.of(List.of(PinNo.of(10), PinNo.of(10), PinNo.of(10)), "X|X|X"));
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForPrint")
    void print(PinNo firstNo, PinNo secondNo, String result) {
        assertThat(PinNoPrinter.print(firstNo, secondNo)).isEqualTo(result);
    }

    private static Stream<Arguments> provideArgumentsForPrint() {
        return Stream.of(
                Arguments.of(PinNo.of(0), null, "-"),
                Arguments.of(PinNo.of(3), null, "3"),
                Arguments.of(PinNo.of(3), PinNo.of(6), "3|6"),
                Arguments.of(PinNo.of(3), PinNo.of(7), "3|/"));
    }
}
