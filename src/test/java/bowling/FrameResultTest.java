package bowling;

import bowling.domain.FrameResult;
import bowling.domain.PointSymbol;
import bowling.domain.PointSymbols;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class FrameResultTest {
    @Test
    void When_New_Created() {
        PointSymbols pointSymbols = new PointSymbols(PointSymbol.STRIKE);
        assertDoesNotThrow(() -> new FrameResult(pointSymbols));
    }

    @Test
    void When_pointSymbols_Then_ReturnPointSymbols() {
        PointSymbols pointSymbols = new PointSymbols(PointSymbol.STRIKE);
        FrameResult frameResult = new FrameResult(pointSymbols);

        assertThat(frameResult.pointSymbols()).isEqualTo(pointSymbols);
    }
}
