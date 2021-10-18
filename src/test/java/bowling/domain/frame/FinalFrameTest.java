package bowling.domain.frame;

import bowling.domain.Left;
import bowling.domain.PinCount;
import bowling.domain.Score;
import bowling.exception.GameEndException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static bowling.domain.frame.FrameNumber.FINAL_NUMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;

public class FinalFrameTest {
    private static final String SEPARATOR = "|";

    private Frame finalFrame;

    @BeforeEach
    void setup() {
        finalFrame = new FinalFrame(FrameNumber.of(FINAL_NUMBER));
    }

    @DisplayName("isFinished, makeIndication 테스트")
    @ParameterizedTest(name = "{index}. {displayName}, arguments: {arguments}")
    @MethodSource("parameterProvider")
    void isFinished_And_MakeIndication(List<PinCount> pinCounts, boolean isFinished, String indication, Score expectedScore) {
        for (PinCount pinCount : pinCounts) {
            finalFrame = finalFrame.play(pinCount);
        }
        final FinalFrame finalFrame1 = (FinalFrame) finalFrame;
        assertAll(
                () -> assertThat(finalFrame1.isFinished()).isEqualTo(isFinished),
                () -> assertThat(finalFrame1.makeIndication()).isEqualTo(indication),
                () -> assertThat(finalFrame1.calculateScore()).isEqualTo(expectedScore)
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
                Arguments.of(oncePitched, false, "5", new Score(5, Left.ONE)),
                Arguments.of(spare, false, "5" + SEPARATOR + "/", Score.SPARE),
                Arguments.of(strike, false, "X", Score.STRIKE),
                Arguments.of(strikeAndSecondPitched, false, "X" + SEPARATOR + "3", new Score(13, Left.ONE)),
                Arguments.of(doubleStrike, false, "X" + SEPARATOR + "X", new Score(20, Left.ONE)),
                Arguments.of(miss, true, "2" + SEPARATOR + "3", new Score(5, Left.ZERO)),
                Arguments.of(spareAndSecondPitched, true, "2" + SEPARATOR + "/" + SEPARATOR + "5", new Score(15, Left.ZERO)),
                Arguments.of(spareAndStrike, true, "2" + SEPARATOR + "/" + SEPARATOR + "X", new Score(20, Left.ZERO)),
                Arguments.of(strikeAndMiss, true, "X" + SEPARATOR + "3" + SEPARATOR + "5", new Score(18, Left.ZERO)),
                Arguments.of(strikeAndSpare, true, "X" + SEPARATOR + "3" + SEPARATOR + "/", new Score(20, Left.ZERO)),
                Arguments.of(doubleAndThirdPitched, true, "X" + SEPARATOR + "X" + SEPARATOR + "7", new Score(27, Left.ZERO)),
                Arguments.of(turkey, true, "X" + SEPARATOR + "X" + SEPARATOR + "X", new Score(30, Left.ZERO))
        );
    }

    @DisplayName("마지막 프레임 종료 후에 play 시도할 경우 GameEndException 발생")
    @Test
    void finalFrame_ThrowsGameEndException_IfPlayAfterGameIsEnd() {
        assertThatExceptionOfType(GameEndException.class).isThrownBy(
                () -> finalFrame.play(PinCount.of(3)).play(PinCount.of(5)).play(PinCount.MAX_PINS)
        );
    }
}
