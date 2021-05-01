package bowling;

import bowling.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

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

    @Test
    void Given_10thFrame_When_isDone_Then_True() {
        Frame frame10th = new FinalFrame(new Pinfall(1), new Pinfall(3));
        Bowling bowling = new Bowling(frame10th);
        assertThat(bowling.isDone()).isTrue();
    }

    @Test
    @DisplayName("9번째 프레임에서 공을 굴렸을 때 다음 프레임에서 보너스 공을 굴릴 수 있는지 테스트")
    void Given_9thFrameRoll_When_Result_Then_ValidResult() {
        Frame frame9th = new NormalFrame(new FrameNumber(9), new Pinfall(1));
        Bowling bowling = new Bowling(frame9th);

        bowling.roll(new Pinfall(2));

        bowling.roll(new Pinfall(10));
        bowling.roll(new Pinfall(10));
        bowling.roll(new Pinfall(10));

        assertThat(bowling.result().result(new FrameNumber(10))).isEqualTo(new FrameResult(new PointSymbols(Arrays.asList(PointSymbol.STRIKE, PointSymbol.STRIKE, PointSymbol.STRIKE))));
    }
}
