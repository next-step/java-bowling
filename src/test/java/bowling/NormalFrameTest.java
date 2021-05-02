package bowling;

import bowling.domain.*;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class NormalFrameTest {
    @Test
    @DisplayName("Class 생성 테스트")
    void When_New_Created() {
        assertDoesNotThrow(() -> new NormalFrame());
    }

    @Test
    @DisplayName("FrameNumber 기본값은 1 테스트")
    void Initial_FrameNumber_Is_One() {
        assertThat(new NormalFrame().frameNumber()).isEqualTo(new FrameNumber(1));
    }

    @Test
    @DisplayName("result를 호출했을 때 FrameResult를 반환하는지 테스트")
    void When_Result_Then_ReturnFrameResult() {
        NormalFrame normalFrame = new NormalFrame();

        assertThat(normalFrame.result())
                .isEqualTo(new FrameResult());
    }

    @Test
    @DisplayName("Strike일 때 FrameResult에 X 심볼 있는지 테스트")
    void Given_Strike_When_Result_Then_ResultWithStrikeSymbol() {
        NormalFrame normalFrame = new NormalFrame(new Pinfall(10));

        assertThat(normalFrame.result().pointSymbols())
                .isEqualTo(new PointSymbols(PointSymbol.STRIKE));
    }

    @Test
    @DisplayName("Spare 때 FrameResult에 / 심볼 있는지 테스트")
    void Given_Spare_When_Result_Then_ResultWithSpareSymbol() {
        NormalFrame normalFrame = new NormalFrame(new Pinfall(9), new Pinfall(1));

        assertThat(normalFrame.result().pointSymbols())
                .isEqualTo(new PointSymbols(PointSymbol.NINE, PointSymbol.SPARE));
    }

    @Test
    @DisplayName("Open 때 FrameResult에 숫자 심볼 있는지 테스트")
    void Given_Open_When_Result_Then_ResultWithNumericSymbol() {
        NormalFrame normalFrame = new NormalFrame(new Pinfall(8), new Pinfall(1));

        assertThat(normalFrame.result().pointSymbols())
                .isEqualTo(new PointSymbols(PointSymbol.EIGHT, PointSymbol.ONE));
    }

    @Test
    @DisplayName("Strike일 때 해당 프레임은 끝났음 테스트")
    void Given_Strike_When_isDone_Then_True() {
        NormalFrame normalFrame = new NormalFrame(new Pinfall(10));

        assertThat(normalFrame.isDone()).isTrue();
    }

    @Test
    @DisplayName("Strike일 때 roll의 결과는 새로운 Frame 테스트")
    void Given_Strike_When_Roll_Then_ResultNewFrame() {
        NormalFrame currentFrame = new NormalFrame();
        Frame newFrame = currentFrame.roll(new Pinfall(10));

        assertThat(newFrame).isNotEqualTo(currentFrame);
    }

    @Test
    @DisplayName("새로운 프레임의 번호는 현재 Frame번호 +1")
    void Given_NewFrame_When_FrameNumber_Then_FrameNumberIsIncreased() {
        NormalFrame currentFrame = new NormalFrame();
        Frame newFrame = currentFrame.roll(new Pinfall(10));

        assertThat(newFrame.frameNumber()).isEqualTo(new FrameNumber(2));
    }

    @Test
    @DisplayName("Open일 때 점수는 넘어진 핀의 개수의 합")
    void Given_Open_When_Score_Then_Score() {
        NormalFrame frame = new NormalFrame(new Pinfall(1), new Pinfall(2));
        assertThat(frame.result().score()).isEqualTo(Score.create(3));
    }

    @Test
    @DisplayName("Strike 일 때 점수는 미확정")
    void Given_Strike_When_Score_Then_NotDetermined() {
        NormalFrame frame = new NormalFrame(new Pinfall(10));
        assertThat(frame.score()).isEqualTo(Score.createNotDetermined());
    }

    @Test
    @DisplayName("Double 일 때 첫 번째 Frame의 점수는 미확정")
    void Given_RollWithStrikeTwice_When_Score_Then_NotDetermined() {
        Frame firstFrame = new NormalFrame();
        Frame secondFrame = firstFrame.roll(new Pinfall(10));
        secondFrame.roll(new Pinfall(10));

        AssertionsForClassTypes.assertThat(firstFrame.score()).isEqualTo(Score.createNotDetermined());
    }

    @Test
    @DisplayName("Triple 일 때 첫 번째 Frame의 점수는 30")
    void Given_RollWithStrikeThrice_When_Score_Then_Score30() {
        Frame firstFrame = new NormalFrame();
        Frame secondFrame = firstFrame.roll(new Pinfall(10));
        Frame thirdFrame = secondFrame.roll(new Pinfall(10));
        thirdFrame.roll(new Pinfall(10));

        AssertionsForClassTypes.assertThat(firstFrame.score()).isEqualTo(Score.create(30));
    }

    @Test
    @DisplayName("Spare 일 때 첫 번째 Frame의 미확정")
    void Given_Spare_When_Score_Then_NotDetermined() {
        NormalFrame frame = new NormalFrame(new Pinfall(9), new Pinfall(1));
        assertThat(frame.score()).isEqualTo(Score.createNotDetermined());
    }

    @Test
    @DisplayName("Spare이고, 다음 Frame의 공을 한 번 던졌을 때 첫 번째 Frame의 점수는 넘어진 핀 개수 + 보너스 점수")
    void Given_SpareAndRollOnce_When_Score_Then_Score12() {
        Frame firstFrame = new NormalFrame(new FrameNumber(1), new Pinfall(9));
        Frame secondFrame = firstFrame.roll(new Pinfall(1));
        secondFrame.roll(new Pinfall(2));
        assertThat(firstFrame.score()).isEqualTo(Score.create(12));
    }

    @Test
    @DisplayName("한 번 던졌을 때 점수 미확정")
    void Given_RollOnce_When_Score_Then_ScoreNotDetermined() {
        Frame frame = new NormalFrame(new FrameNumber(1));
        assertThat(frame.score()).isEqualTo(Score.createNotDetermined());
    }
}
