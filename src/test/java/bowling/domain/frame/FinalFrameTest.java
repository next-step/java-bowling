package bowling.domain.frame;

import bowling.domain.Indication;
import bowling.domain.PinCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class FinalFrameTest {

    @DisplayName("isFinished, makeIndication 테스트")
    @ParameterizedTest(name = "{index}. {displayName}, arguments: {arguments}")
    @MethodSource("parameterProvider")
    void isFinished_And_MakeIndication(List<PinCount> pinCounts, boolean isFinished, String indication) {
        FinalFrame finalFrame = new FinalFrame();
        for (PinCount pinCount : pinCounts) {
            finalFrame = finalFrame.play(pinCount);
        }
        final FinalFrame finalFrame1 = finalFrame;
        assertAll(
                () -> assertThat(finalFrame1.isFinished()).isEqualTo(isFinished),
                () -> assertThat(finalFrame1.makeIndication()).isEqualTo(indication)
        );
    }

    private static Stream<Arguments> parameterProvider() {
        List<PinCount> oncePitched = Collections.singletonList(PinCount.of(5));
        List<PinCount> spare = Arrays.asList(PinCount.of(5), PinCount.of(5));
        List<PinCount> strike = Collections.singletonList(PinCount.MAX_PINS);
        List<PinCount> strikeAndSecondPitched = Arrays.asList(PinCount.MAX_PINS, PinCount.of(3));
        List<PinCount> doubleStrike = Arrays.asList(PinCount.MAX_PINS, PinCount.MAX_PINS);
        List<PinCount> miss = Arrays.asList(PinCount.of(2), PinCount.of(3));
        List<PinCount> spareAndSecondPitched = Arrays.asList(PinCount.of(2), PinCount.of(8), PinCount.of(5));
        List<PinCount> spareAndStrike = Arrays.asList(PinCount.of(2), PinCount.of(8), PinCount.MAX_PINS);
        List<PinCount> strikeAndMiss = Arrays.asList(PinCount.MAX_PINS, PinCount.of(3), PinCount.of(5));
        List<PinCount> strikeAndSpare = Arrays.asList(PinCount.MAX_PINS, PinCount.of(3), PinCount.of(7));
        List<PinCount> doubleAndThirdPitched = Arrays.asList(PinCount.MAX_PINS, PinCount.MAX_PINS, PinCount.of(7));
        List<PinCount> turkey = Arrays.asList(PinCount.MAX_PINS, PinCount.MAX_PINS, PinCount.MAX_PINS);
        return Stream.of(
                Arguments.of(oncePitched, false, "5"),
                Arguments.of(spare, false, "5" + Indication.SEPARATOR + "/"),
                Arguments.of(strike, false, "X"),
                Arguments.of(strikeAndSecondPitched, false, "X" + Indication.SEPARATOR + "3"),
                Arguments.of(doubleStrike, false, "X" + Indication.SEPARATOR + "X"),
                Arguments.of(miss, true, "2" + Indication.SEPARATOR + "3"),
                Arguments.of(spareAndSecondPitched, true, "2" + Indication.SEPARATOR + "/" + Indication.SEPARATOR + "5"),
                Arguments.of(spareAndStrike, true, "2" + Indication.SEPARATOR + "/" + Indication.SEPARATOR + "X"),
                Arguments.of(strikeAndMiss, true, "X" + Indication.SEPARATOR + "3" + Indication.SEPARATOR + "5"),
                Arguments.of(strikeAndSpare, true, "X" + Indication.SEPARATOR + "3" + Indication.SEPARATOR + "/"),
                Arguments.of(doubleAndThirdPitched, true, "X" + Indication.SEPARATOR + "X" + Indication.SEPARATOR + "7"),
                Arguments.of(turkey, true, "X" + Indication.SEPARATOR + "X" + Indication.SEPARATOR + "X")
        );
    }
}
