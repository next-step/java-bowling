package bowling;

import bowling.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class BowlingResultTest {
    @Test
    void When_New_Then_Created() {
        assertDoesNotThrow(() -> new BowlingResult());
    }

    @Test
    void Given_FrameResult_When_New_Then_Created() {
        assertDoesNotThrow(() -> new BowlingResult(Arrays.asList(new FrameResult(), new FrameResult())));
    }

    @Test
    void When_frameResults_Then_ReturnsFrameResults() {
        List<FrameResult> frameResults = Arrays.asList(new FrameResult(), new FrameResult(new PointSymbols(PointSymbol.STRIKE)));

        BowlingResult bowlingResult = new BowlingResult(frameResults);

        assertThat(bowlingResult.results()).isEqualTo(frameResults);
    }

    @Test
    void Given_frameNumber_When_result_Then_SingleFrameResult() {
        List<FrameResult> frameResults = Arrays.asList(new FrameResult(), new FrameResult(new PointSymbols(PointSymbol.STRIKE)));
        BowlingResult bowlingResult = new BowlingResult(frameResults);

        assertThat(bowlingResult.result(new FrameNumber(2))).isEqualTo(new FrameResult(new PointSymbols(PointSymbol.STRIKE)));
    }

    @Test
    @DisplayName("FrameResults가 없는 Frame의 결과를 요청하면 빈 FrameResult를 반환")
    void Given_WrongFrameNumber_When_Result_Then_EmptyFrameResult() {
        List<FrameResult> frameResults = Arrays.asList(new FrameResult(), new FrameResult(new PointSymbols(PointSymbol.STRIKE)));
        BowlingResult bowlingResult = new BowlingResult(frameResults);

        assertThat(bowlingResult.result(new FrameNumber(3))).isEqualTo(new FrameResult());
    }

    @Test
    void When_Add_Then_FrameResultAdded() {
        FrameResult frameResult = new FrameResult(new PointSymbols(PointSymbol.STRIKE));
        BowlingResult bowlingResult = new BowlingResult();

        bowlingResult.add(new FrameNumber(1), frameResult);

        assertThat(bowlingResult.result(new FrameNumber(1))).isEqualTo(frameResult);
    }

    @Test
    void Given_NewFrameResult_When_Add_Then_ReplaceFrameResult() {
        BowlingResult bowlingResult = new BowlingResult();

        FrameResult oldFrameResult = new FrameResult(new PointSymbols(PointSymbol.SPARE));
        bowlingResult.add(new FrameNumber(1), oldFrameResult);

        FrameResult newFrameResult = new FrameResult(new PointSymbols(PointSymbol.STRIKE));
        bowlingResult.add(new FrameNumber(1), newFrameResult);
        assertThat(bowlingResult.result(new FrameNumber(1))).isEqualTo(newFrameResult);
    }
}
