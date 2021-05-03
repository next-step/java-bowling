package bowling;

import bowling.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class FinalFrameTest {
    @Test
    @DisplayName("Class 생성 테스트")
    void When_New_Then_Created() {
        assertDoesNotThrow(() -> new FinalFrame(new FrameNumber(10)));
    }

    @Test
    @DisplayName("마지막 프레임의 프레임 번호가 10이 아닐 때 Exception")
    void Given_FrameNumber1_When_New_Then_Exception() {
        assertThatExceptionOfType(WrongFrameNumberException.class)
                .isThrownBy(() -> new FinalFrame(new FrameNumber(1)))
                .withMessage("프레임번호가 잘못되었습니다");
    }

    @Test
    @DisplayName("result를 호출했을 때 FrameResult를 반환하는지 테스트")
    void When_Result_Then_FrameResult() {
        assertThat(new FinalFrame().result()).isEqualTo(new SingleFrameResult());
    }

    @Test
    @DisplayName("Strike일 때 FrameResult에 X 심볼 있는지 테스트")
    void Given_Strike_When_Result_Then_WithStrikeSymbol() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.roll(new Pinfall(10));
        assertThat(finalFrame.result().pointSymbols())
                .isEqualTo(new PointSymbols(PointSymbol.STRIKE));
    }

    @Test
    @DisplayName("Double 일 때 FrameResult에 XX 심볼 있는지 테스트")
    void Given_Double_When_Result_Then_WithTwoStrikeSymbol() {
        FinalFrame finalFrame = new FinalFrame(new Pinfall(10), new Pinfall(10));

        assertThat(finalFrame.result().pointSymbols())
                .isEqualTo(new PointSymbols(PointSymbol.STRIKE, PointSymbol.STRIKE));
    }

    @Test
    @DisplayName("Strike를 한 번 쳤을 때 점수는 미확정")
    void Given_Strike_When_Score_Then_NotDetermined() {
        FinalFrame finalFrame = new FinalFrame(new Pinfall(10));
        assertThat(finalFrame.score()).isEqualTo(Score.createNotDetermined());
    }

    @Test
    @DisplayName("Strike를 두 번 쳤을 때 점수는 미확정")
    void Given_Double_When_Score_Then_NotDetermined() {
        FinalFrame finalFrame = new FinalFrame(new Pinfall(10), new Pinfall(10));
        assertThat(finalFrame.score()).isEqualTo(Score.createNotDetermined());
    }

    @Test
    @DisplayName("Strike를 세 번 쳤을 때 점수는 30점")
    void Given_Triple_When_Score_Then_Score30() {
        FinalFrame finalFrame = new FinalFrame(new Pinfall(10), new Pinfall(10));
        finalFrame.roll(new Pinfall(10));
        assertThat(finalFrame.score()).isEqualTo(Score.create(30));
    }
}
