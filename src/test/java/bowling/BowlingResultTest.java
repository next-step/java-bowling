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
    @DisplayName("구조체 생성 테스트")
    void When_New_Then_Created() {
        assertDoesNotThrow(() -> new BowlingResult());
    }

    @Test
    @DisplayName("N개의 프레임 결과를 받아서 생성 테스트")
    void Given_FrameResult_When_New_Then_Created() {
        assertDoesNotThrow(() -> new BowlingResult(Arrays.asList(new SingleFrameResult(), new SingleFrameResult())));
    }

    @Test
    @DisplayName("Result를 호출했을 때 입력한 Result가 반환되는지 테스트")
    void When_frameResults_Then_ReturnsFrameResults() {
        List<SingleFrameResult> singleFrameResults = Arrays.asList(new SingleFrameResult(), new SingleFrameResult(new PointSymbols(PointSymbol.STRIKE)));

        BowlingResult bowlingResult = new BowlingResult(singleFrameResults);

        assertThat(bowlingResult.results()).isEqualTo(singleFrameResults);
    }

    @Test
    @DisplayName("특정 Frame의 결과 반환 테스트")
    void Given_frameNumber_When_result_Then_SingleFrameResult() {
        List<SingleFrameResult> singleFrameResults = Arrays.asList(new SingleFrameResult(), new SingleFrameResult(new PointSymbols(PointSymbol.STRIKE)));
        BowlingResult bowlingResult = new BowlingResult(singleFrameResults);

        assertThat(bowlingResult.result(new FrameNumber(2))).isEqualTo(new SingleFrameResult(new PointSymbols(PointSymbol.STRIKE)));
    }

    @Test
    @DisplayName("FrameResults가 없는 Frame의 결과를 요청하면 빈 FrameResult를 반환")
    void Given_WrongFrameNumber_When_Result_Then_EmptyFrameResult() {
        List<SingleFrameResult> singleFrameResults = Arrays.asList(new SingleFrameResult(), new SingleFrameResult(new PointSymbols(PointSymbol.STRIKE)));
        BowlingResult bowlingResult = new BowlingResult(singleFrameResults);

        assertThat(bowlingResult.result(new FrameNumber(3))).isEqualTo(new SingleFrameResult());
    }

    @Test
    @DisplayName("FrameResult를 추가했을 때, 추가 되는지 테스트")
    void When_Add_Then_FrameResultAdded() {
        SingleFrameResult singleFrameResult = new SingleFrameResult(new PointSymbols(PointSymbol.STRIKE));
        BowlingResult bowlingResult = new BowlingResult();

        bowlingResult.add(new FrameNumber(1), singleFrameResult);

        assertThat(bowlingResult.result(new FrameNumber(1))).isEqualTo(singleFrameResult);
    }

    @Test
    @DisplayName("기존에 있던 Frame의 FrameResult를 추가했을 때, 변경되는지 테스트")
    void Given_NewFrameResult_When_Add_Then_ReplaceFrameResult() {
        BowlingResult bowlingResult = new BowlingResult();

        SingleFrameResult oldSingleFrameResult = new SingleFrameResult(new PointSymbols(PointSymbol.SPARE));
        bowlingResult.add(new FrameNumber(1), oldSingleFrameResult);

        SingleFrameResult newSingleFrameResult = new SingleFrameResult(new PointSymbols(PointSymbol.STRIKE));
        bowlingResult.add(new FrameNumber(1), newSingleFrameResult);
        assertThat(bowlingResult.result(new FrameNumber(1))).isEqualTo(newSingleFrameResult);
    }
}
