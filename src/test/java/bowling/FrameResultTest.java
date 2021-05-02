package bowling;

import bowling.domain.FrameResult;
import bowling.domain.PointSymbol;
import bowling.domain.PointSymbols;
import bowling.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class FrameResultTest {
    @Test
    @DisplayName("Class 생성 테스트")
    void When_New_Created() {
        PointSymbols pointSymbols = new PointSymbols(PointSymbol.STRIKE);
        assertDoesNotThrow(() -> new FrameResult(pointSymbols));
    }

    @Test
    @DisplayName("PointSymols을 호출 했을 때 입력한 PointSymbols을 잘 가지고 있는지 테스트")
    void When_pointSymbols_Then_ReturnPointSymbols() {
        PointSymbols pointSymbols = new PointSymbols(PointSymbol.STRIKE);
        FrameResult frameResult = new FrameResult(pointSymbols);

        assertThat(frameResult.pointSymbols()).isEqualTo(pointSymbols);
    }

    @Test
    @DisplayName("초기 점수는 미확정")
    void Given_Initial_When_Score_Then_NotDetermined() {
        assertThat(new FrameResult().score()).isEqualTo(Score.createNotDetermined());
    }
}
