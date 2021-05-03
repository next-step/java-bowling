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
        assertDoesNotThrow(() -> new BowlingResult(Arrays.asList(new FrameResult(), new FrameResult())));
    }

    @Test
    @DisplayName("OpenFrame이면 AggregatedScore는 OpenFrame의 점수와 같다")
    void Given_OneScoreSingleFrameResult_When_New_Then_ValidFrameResult() {
        FrameResult openFrameResult = new FrameResult(Score.create(1));
        BowlingResult bowlingResult = new BowlingResult(Arrays.asList(openFrameResult));
        assertThat(bowlingResult.result(new FrameNumber(1)).score()).isEqualTo(openFrameResult.score());
    }

    @Test
    @DisplayName("특정 Frame의 결과 반환 테스트")
    void Given_frameNumber_When_result_Then_SingleFrameResult() {
        List<FrameResult> singleFrameResults = Arrays.asList(new FrameResult(), new FrameResult(new PointSymbols(PointSymbol.STRIKE)));
        BowlingResult bowlingResult = new BowlingResult(singleFrameResults);

        assertThat(bowlingResult.result(new FrameNumber(2)).pointSymbols()).isEqualTo(new PointSymbols(PointSymbol.STRIKE));
    }

    @Test
    @DisplayName("FrameResults가 없는 Frame의 결과를 요청하면 빈 FrameResult를 반환")
    void Given_WrongFrameNumber_When_Result_Then_EmptyFrameResult() {
        List<FrameResult> singleFrameResults = Arrays.asList(new FrameResult(), new FrameResult(new PointSymbols(PointSymbol.STRIKE)));
        BowlingResult bowlingResult = new BowlingResult(singleFrameResults);

        assertThat(bowlingResult.result(new FrameNumber(3))).isEqualTo(new FrameResult());
    }

    @Test
    @DisplayName("FrameResult를 추가했을 때, 추가 되는지 테스트")
    void When_Add_Then_FrameResultAdded() {
        BowlingResult bowlingResult = new BowlingResult();

        bowlingResult.add(new FrameNumber(1), new PointSymbols(PointSymbol.NINE), Score.create(9));

        assertThat(bowlingResult.result(new FrameNumber(1))).isEqualTo(new FrameResult(new PointSymbols(PointSymbol.NINE), Score.create(9)));
    }

    @Test
    @DisplayName("기존에 있던 Frame의 FrameResult를 추가했을 때, 변경되는지 테스트")
    void Given_NewFrameResult_When_Add_Then_ReplaceFrameResult() {
        BowlingResult bowlingResult = new BowlingResult();

        bowlingResult.add(new FrameNumber(1), new PointSymbols(PointSymbol.ONE), Score.create(1));
        bowlingResult.add(new FrameNumber(1), new PointSymbols(PointSymbol.TWO), Score.create(2));
        assertThat(bowlingResult.result(new FrameNumber(1))).isEqualTo(new FrameResult(new PointSymbols(PointSymbol.TWO), Score.create(2)));
    }

    @Test
    @DisplayName("Player 이름이 설정되었을 때 이름을 잘 가지고 오는지 테스트")
    void Given_Player_When_Player_Then_ReturnGivenPlayerName() {
        Player player = new Player("abc");

        BowlingResult bowlingResult = new BowlingResult(player);

        assertThat(bowlingResult.player()).isEqualTo(player);
    }
}
