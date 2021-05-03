package bowling;

import bowling.domain.FrameResult;
import bowling.domain.PointSymbol;
import bowling.domain.PointSymbols;
import bowling.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class FrameResultTest {
    @Test
    @DisplayName("생성자 테스트")
    void When_New_Then_Created() {
        assertThat(new FrameResult()).isEqualTo(new FrameResult());
    }

    @Test
    @DisplayName("입력한 Score를 잘 가지고 있는지 테스트")
    void Given_AggregatedScore_When_aggregatedScore_Then_GivenScore() {
        Score aggregatedScore = Score.create(10);

        FrameResult frameResult = new FrameResult(new PointSymbols(), aggregatedScore);
        assertThat(frameResult.score()).isEqualTo(aggregatedScore);
    }

    @Test
    @DisplayName("입력한 PointSymbols를 잘 가지고 있는지 테스트")
    void Given_PointSymbols_When_PointSymbols_Then_GivenPointSymbols() {
        PointSymbols pointSymbols = new PointSymbols(PointSymbol.ONE, PointSymbol.TWO);

        FrameResult frameResult = new FrameResult(pointSymbols, Score.create(3));
        assertThat(frameResult.pointSymbols()).isEqualTo(pointSymbols);
    }
}
