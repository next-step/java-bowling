package bowling;

import bowling.domain.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class BowlingTest {
    @Test
    void When_New_Then_Created() {
        assertDoesNotThrow(() -> new Bowling());
    }

    @Test
    void When_Result_Then_ResultBowlingResult() {
        assertThat(new Bowling().result()).isEqualTo(new BowlingResult());
    }

    @Test
    void Initial_FrameNumber_Is_One() {
        assertThat(new Bowling().frameNumber()).isEqualTo(new FrameNumber(1));
    }

    @Test
    void Given_Strike_When_Roll_Then_FrameNumberIncreased() {
        Bowling bowling = new Bowling();
        bowling.roll(new Pinfall(10));
        assertThat(bowling.frameNumber()).isEqualTo(new FrameNumber(2));
    }

    @Test
    void Given_Roll_When_Result_Then_FrameResultUpdated() {
        Bowling bowling = new Bowling();
        bowling.roll(new Pinfall(9));
        assertThat(bowling.result().result(new FrameNumber(1))).isEqualTo(new FrameResult(new PointSymbols(PointSymbol.NINE)));

        bowling.roll(new Pinfall(1));
        assertThat(bowling.result().result(new FrameNumber(1))).isEqualTo(new FrameResult(new PointSymbols(PointSymbol.NINE, PointSymbol.SPARE)));

        bowling.roll(new Pinfall(10));
        assertThat(bowling.result().result(new FrameNumber(1))).isEqualTo(new FrameResult(new PointSymbols(PointSymbol.NINE, PointSymbol.SPARE)));
        assertThat(bowling.result().result(new FrameNumber(2))).isEqualTo(new FrameResult(new PointSymbols(PointSymbol.STRIKE)));
    }
}
