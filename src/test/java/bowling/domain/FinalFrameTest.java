package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FinalFrameTest {

    @Test
    @DisplayName("10프레임 정상 생성 테스트")
    void finalFrameMakeTest() {
        Frame finalFrame = new FinalFrame(10);
        assertThat(finalFrame).isEqualTo(new FinalFrame(10));
    }

    @Test
    @DisplayName("10프레임 투구 테스트")
    void finalFrameBowlTest() {
        Frame finalFrame = new FinalFrame(10);
        finalFrame.bowl(5);
        finalFrame.bowl(5);
        finalFrame.bowl(10);
        assertThat(finalFrame.pinStatus().totalPin()).isEqualTo(20);
        assertThat(finalFrame.pinStatus().firstPin()).isEqualTo(5);
        assertThat(finalFrame.pinStatus().secondPin()).isEqualTo(5);
        assertThat(finalFrame.pinStatus().bonusPin()).isEqualTo(10);

        assertThat(Score.score(finalFrame.pinStatus().pinSize(2), finalFrame.pinStatus().pinSize(1), finalFrame.pinStatus().pinSize(0), 3)).isEqualTo(Score.STRIKE);

    }

    @Test
    @DisplayName("10프레임 투구 테스트2")
    void finalFrameBowlTest2() {
        Frame finalFrame = new FinalFrame(10);
        finalFrame.bowl(5);
        finalFrame.bowl(5);
        finalFrame.bowl(5);

        assertThat(Score.score(finalFrame.pinStatus().pinSize(0), 0, 0, 1)).isEqualTo(Score.MISS);
        assertThat(Score.score(finalFrame.pinStatus().pinSize(1), finalFrame.pinStatus().pinSize(0), 0, 2)).isEqualTo(Score.SPARE);
        assertThat(Score.score(finalFrame.pinStatus().pinSize(2), finalFrame.pinStatus().pinSize(1), finalFrame.pinStatus().pinSize(0), 3)).isEqualTo(Score.MISS);

    }

    @Test
    @DisplayName("10프레임 투구 테스트3")
    void finalFrameBowlTest3() {
        Frame finalFrame = new FinalFrame(10);
        finalFrame.bowl(10);
        finalFrame.bowl(5);
        finalFrame.bowl(5);

        assertThat(Score.score(finalFrame.pinStatus().pinSize(0), 0, 0, 1)).isEqualTo(Score.STRIKE);
        assertThat(Score.score(finalFrame.pinStatus().pinSize(1), finalFrame.pinStatus().pinSize(0), 0, 2)).isEqualTo(Score.MISS);
        assertThat(Score.score(finalFrame.pinStatus().pinSize(2), finalFrame.pinStatus().pinSize(1), finalFrame.pinStatus().pinSize(0), 3)).isEqualTo(Score.SPARE);

    }

    @Test
    @DisplayName("10프레임 투구 테스트4")
    void finalFrameBowlTest4() {
        Frame finalFrame = new FinalFrame(10);
        finalFrame.bowl(10);
        finalFrame.bowl(10);
        finalFrame.bowl(5);

        assertThat(Score.score(finalFrame.pinStatus().pinSize(0), 0, 0, 1)).isEqualTo(Score.STRIKE);
        assertThat(Score.score(finalFrame.pinStatus().pinSize(1), finalFrame.pinStatus().pinSize(0), 0, 2)).isEqualTo(Score.STRIKE);
        assertThat(Score.score(finalFrame.pinStatus().pinSize(2), finalFrame.pinStatus().pinSize(1), finalFrame.pinStatus().pinSize(0), 3)).isEqualTo(Score.MISS);

    }

    @Test
    @DisplayName("10프레임 투구 테스트5")
    void finalFrameBowlTest5() {
        Frame finalFrame = new FinalFrame(10);
        finalFrame.bowl(0);
        finalFrame.bowl(10);
        finalFrame.bowl(10);

        assertThat(Score.score(finalFrame.pinStatus().pinSize(0), 0, 0, 1)).isEqualTo(Score.GUTTER);
        assertThat(Score.score(finalFrame.pinStatus().pinSize(1), finalFrame.pinStatus().pinSize(0), 0, 2)).isEqualTo(Score.SPARE);
        assertThat(Score.score(finalFrame.pinStatus().pinSize(2), finalFrame.pinStatus().pinSize(1), finalFrame.pinStatus().pinSize(0), 3)).isEqualTo(Score.STRIKE);

    }

    @Test
    @DisplayName("10프레임 투구 테스트6")
    void finalFrameBowlTest6() {
        Frame finalFrame = new FinalFrame(10);
        finalFrame.bowl(0);
        finalFrame.bowl(0);

        assertThat(Score.score(finalFrame.pinStatus().pinSize(0), 0, 0, 1)).isEqualTo(Score.GUTTER);
        assertThat(Score.score(finalFrame.pinStatus().pinSize(1), finalFrame.pinStatus().pinSize(0), 0, 2)).isEqualTo(Score.GUTTER);

    }

    @Test
    @DisplayName("10프레임 투구 테스트7")
    void finalFrameBowlTest7() {
        Frame finalFrame = new FinalFrame(10);
        finalFrame.bowl(10);
        finalFrame.bowl(0);

        assertThat(Score.score(finalFrame.pinStatus().pinSize(0), 0, 0, 1)).isEqualTo(Score.STRIKE);
        assertThat(Score.score(finalFrame.pinStatus().pinSize(1), finalFrame.pinStatus().pinSize(0), 0, 2)).isEqualTo(Score.GUTTER);

    }

    @Test
    @DisplayName("10프레임 투구 테스트8")
    void finalFrameBowlTest8() {
        Frame finalFrame = new FinalFrame(10);
        finalFrame.bowl(10);
        finalFrame.bowl(7);


        assertThat(Score.score(finalFrame.pinStatus().pinSize(0), 0, 0, 1)).isEqualTo(Score.STRIKE);
        assertThat(Score.score(finalFrame.pinStatus().pinSize(1), finalFrame.pinStatus().pinSize(0), 0, 2)).isEqualTo(Score.MISS);

    }

    @Test
    @DisplayName("10프레임 투구 테스트 : 횟수 초과")
    void invalidFinalFrameBowlTest() {
        assertThatThrownBy(() -> {
            Frame finalFrame = new FinalFrame(10);
            finalFrame.bowl(0);
            finalFrame.bowl(5);
            finalFrame.bowl(10);
        }).isInstanceOf(RuntimeException.class).hasMessage("횟수를 초과했습니다.");
    }

    @Test
    @DisplayName("10프레임 투구 테스트 : 횟수 초과2")
    void invalidFinalFrameBowlTest2() {
        assertThatThrownBy(() -> {
            Frame finalFrame = new FinalFrame(10);
            finalFrame.bowl(0);
            finalFrame.bowl(0);
            finalFrame.bowl(10);
        }).isInstanceOf(RuntimeException.class).hasMessage("횟수를 초과했습니다.");
    }
}
