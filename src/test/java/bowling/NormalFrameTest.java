package bowling;

import bowling.domain.*;
import org.assertj.core.api.AssertionsForClassTypes;
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
    void Initial_FrameNumber_Is_One() {
        assertThat(new NormalFrame().frameNumber()).isEqualTo(new FrameNumber(1));
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

    @Test
    void Given_Strike_When_Roll_Then_ResultNewFrame() {
        NormalFrame currentFrame = new NormalFrame();
        Frame newFrame = currentFrame.roll(new Pinfall(10));

        assertThat(newFrame).isNotEqualTo(currentFrame);
    }

    @Test
    void Given_NewFrame_When_FrameNumber_Then_FrameNumberIsIncreased() {
        NormalFrame currentFrame = new NormalFrame();
        Frame newFrame = currentFrame.roll(new Pinfall(10));

        assertThat(newFrame.frameNumber()).isEqualTo(new FrameNumber(2));
    }

    @Test
    void Given_Open_When_Score_Then_Score() {
        NormalFrame frame = new NormalFrame(new Pinfall(1), new Pinfall(2));
        assertThat(frame.result().score()).isEqualTo(Score.create(3));
    }

    @Test
    void Given_Strike_When_Score_Then_NotDetermined() {
        NormalFrame frame = new NormalFrame(new Pinfall(10));
        assertThat(frame.score()).isEqualTo(Score.createNotDetermined());
    }

    @Test
    void Given_RollWithStrikeTwice_When_Score_Then_NotDetermined() {
        Frame firstFrame = new NormalFrame();
        Frame secondFrame = firstFrame.roll(new Pinfall(10));
        secondFrame.roll(new Pinfall(10));

        AssertionsForClassTypes.assertThat(firstFrame.score()).isEqualTo(Score.createNotDetermined());
    }

    @Test
    void Given_RollWithStrikeThrice_When_Score_Then_Score30() {
        Frame firstFrame = new NormalFrame();
        Frame secondFrame = firstFrame.roll(new Pinfall(10));
        Frame thirdFrame = secondFrame.roll(new Pinfall(10));
        thirdFrame.roll(new Pinfall(10));

        AssertionsForClassTypes.assertThat(firstFrame.score()).isEqualTo(Score.create(30));
    }

    @Test
    void Given_Spare_When_Score_Then_NotDetermined() {
        NormalFrame frame = new NormalFrame(new Pinfall(9), new Pinfall(1));
        assertThat(frame.score()).isEqualTo(Score.createNotDetermined());
    }

    @Test
    void Given_SpareAndRollOnce_When_Score_Then_Score12() {
        Frame firstFrame = new NormalFrame(new FrameNumber(1), new Pinfall(9));
        Frame secondFrame = firstFrame.roll(new Pinfall(1));
        secondFrame.roll(new Pinfall(2));
        assertThat(firstFrame.score()).isEqualTo(Score.create(12));
    }

    @Test
    void Given_RollOnce_When_Score_Then_ScoreNotDetermined() {
        Frame frame = new NormalFrame(new FrameNumber(1));
        assertThat(frame.score()).isEqualTo(Score.createNotDetermined());
    }
}
