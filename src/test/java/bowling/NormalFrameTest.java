package bowling;

import bowling.domain.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class NormalFrameTest {
    @Test
    void When_New_Created() {
        assertDoesNotThrow(() -> new NormalFrame());
    }

    @Test
    void When_Result_Then_ReturnFrameResult() {
        NormalFrame normalFrame = new NormalFrame();

        assertThat(normalFrame.result())
                .isEqualTo(new FrameResult());
    }

    @Test
    void Given_Strike_When_Result_Then_ResultWithStrikeSymbol() {
        NormalFrame normalFrame = new NormalFrame(new Pinfall(10));

        assertThat(normalFrame.result().pointSymbols())
                .isEqualTo(new PointSymbols(PointSymbol.STRIKE));
    }

    @Test
    void Given_Spare_When_Result_Then_ResultWithSpareSymbol() {
        NormalFrame normalFrame = new NormalFrame(new Pinfall(9), new Pinfall(1));

        assertThat(normalFrame.result().pointSymbols())
                .isEqualTo(new PointSymbols(PointSymbol.NINE, PointSymbol.SPARE));
    }

    @Test
    void Given_Open_When_Result_Then_ResultWithNumericSymbol() {
        NormalFrame normalFrame = new NormalFrame(new Pinfall(8), new Pinfall(1));

        assertThat(normalFrame.result().pointSymbols())
                .isEqualTo(new PointSymbols(PointSymbol.EIGHT, PointSymbol.ONE));
    }

    @Test
    void Given_Strike_When_isDone_Then_True() {
        NormalFrame normalFrame = new NormalFrame(new Pinfall(10));

        assertThat(normalFrame.isDone()).isTrue();
    }
}
