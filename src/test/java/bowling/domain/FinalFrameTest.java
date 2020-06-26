package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("FinalFrame 테스트")
class FinalFrameTest {

    private final int FINAL_FRAME_NUMBER = 10;

    @DisplayName("FinalFrame 생성")
    @Test
    void FinalFrame() {
        FinalFrame finalFrame = new FinalFrame(FINAL_FRAME_NUMBER);

        assertThat(finalFrame).isInstanceOf(FinalFrame.class);
        assertThat(finalFrame.getFrameNumber()).isEqualTo(FINAL_FRAME_NUMBER);
    }

    @DisplayName("두번째 투구 가능여부 확인")
    @Test
    void isAbleSecondTry() {
        FinalFrame finalFrame = new FinalFrame(FINAL_FRAME_NUMBER);

        assertThat(finalFrame.isAbleSecondTry()).isTrue();
    }

    @DisplayName("보너스 투구 가능여부 확인 - 첫번째 투구 스트라이크")
    @Test
    void isAbleBonusTry1() {
        FinalFrame finalFrame = new FinalFrame(FINAL_FRAME_NUMBER);
        finalFrame.firstTry(ScoreText.STRIKE.getScore());
        assertThat(finalFrame.isAbleBonusTry()).isTrue();
    }

    @DisplayName("보너스 투구 가능여부 확인 - 두번째 투구에 스페어처리")
    @Test
    void isAbleBonusTry2() {
        FinalFrame finalFrame = new FinalFrame(FINAL_FRAME_NUMBER);
        finalFrame.firstTry(ScoreText.FOUR.getScore());
        finalFrame.secondTry(ScoreText.SIX.getScore());

        assertThat(finalFrame.isAbleBonusTry()).isTrue();
    }

    @DisplayName("결과 출력 확인")
    @Test
    void testToString() {
        FinalFrame finalFrame = new FinalFrame(FINAL_FRAME_NUMBER);
        finalFrame.firstTry(ScoreText.FOUR.getScore());
        finalFrame.secondTry(ScoreText.SIX.getScore());
        finalFrame.bonusTry(ScoreText.STRIKE.getScore());

        assertThat(finalFrame.toString()).isEqualTo("4|/|X");
    }
}
