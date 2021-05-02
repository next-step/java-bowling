package bowling;

import bowling.domain.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class FinalFrameTest {
    @Test
    void When_New_Then_Created() {
        assertDoesNotThrow(() -> new FinalFrame(new FrameNumber(10)));
    }

    @Test
    void Given_FrameNumber1_When_New_Then_Exception() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new FinalFrame(new FrameNumber(1)))
                .withMessage("프레임번호가 잘못되었습니다");
    }

    @Test
    void When_Result_Then_FrameResult() {
        assertThat(new FinalFrame().result()).isEqualTo(new FrameResult());
    }

    @Test
    void Given_Strike_When_Result_Then_WithStrikeSymbol() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.roll(new Pinfall(10));
        assertThat(finalFrame.result().pointSymbols())
                .isEqualTo(new PointSymbols(PointSymbol.STRIKE));
    }

    @Test
    void Given_TwoStrike_When_Result_Then_WithTwoStrikeSymbol() {
        FinalFrame finalFrame = new FinalFrame(new Pinfall(10), new Pinfall(10));

        assertThat(finalFrame.result().pointSymbols())
                .isEqualTo(new PointSymbols(PointSymbol.STRIKE, PointSymbol.STRIKE));
    }

    @Test
    void Given_Strike_When_Score_Then_NotDetermined() {
        FinalFrame finalFrame = new FinalFrame(new Pinfall(10));
        assertThat(finalFrame.score()).isEqualTo(Score.createNotDetermined());
    }

    @Test
    void Given_Double_When_Score_Then_NotDetermined() {
        FinalFrame finalFrame = new FinalFrame(new Pinfall(10), new Pinfall(10));
        assertThat(finalFrame.score()).isEqualTo(Score.createNotDetermined());
    }

    @Test
    void Given_Triple_When_Score_Then_Score30() {
        FinalFrame finalFrame = new FinalFrame(new Pinfall(10), new Pinfall(10));
        finalFrame.roll(new Pinfall(10));
        assertThat(finalFrame.score()).isEqualTo(Score.create(30));
    }
}
