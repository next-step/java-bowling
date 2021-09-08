package bowling.domain;

import bowling.exception.InvalidScoreException;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FinalFrameTest {
    @DisplayName("FinalFrame 생성 성공 테스트")
    @Test
    public void create_finalFrame() {
        FinalFrame finalFrame = new FinalFrame(3);
        assertThat(finalFrame).isEqualTo(new FinalFrame(3));
        assertThat(finalFrame.isNext()).isEqualTo(false);
    }

    @DisplayName("FinalFrame 생성 실패 테스트(핀 갯수 초과)")
    @Test
    public void create_finalFrame_error() {
        assertThatThrownBy(() -> new FinalFrame(11))
                .isInstanceOf(InvalidScoreException.class);
    }

    @DisplayName("FinalFrame 두번째 투구 실패 테스트(핀 갯수 초과)")
    @Test
    public void create_finalFrame_pitching_error() {
        FinalFrame finalFrame = new FinalFrame(3);
        assertThat(finalFrame.isNext()).isEqualTo(false);

        assertThatThrownBy(() -> finalFrame.addScore(8))
                .isInstanceOf(InvalidScoreException.class);
    }

    @DisplayName("FinalFrame 스트라이크로 3번 투구 테스트")
    @Test
    public void strike_finalFrame() {
        FinalFrame finalFrame = new FinalFrame(10);
        assertThat(finalFrame.sumOfScore()).isEqualTo(10);
        assertThat(finalFrame.scoreToSymbol()).isEqualTo("X");
        assertThat(finalFrame.isNext()).isEqualTo(false);

        finalFrame.addScore(10);
        assertThat(finalFrame.sumOfScore()).isEqualTo(20);
        assertThat(finalFrame.scoreToSymbol()).isEqualTo("X|X");
        assertThat(finalFrame.isNext()).isEqualTo(false);

        finalFrame.addScore(10);
        assertThat(finalFrame.sumOfScore()).isEqualTo(30);
        assertThat(finalFrame.scoreToSymbol()).isEqualTo("X|X|X");
        assertThat(finalFrame.isNext()).isEqualTo(true);
    }

    @DisplayName("FinalFrame 스페어로 3번 투구 테스트")
    @Test
    public void spare_finalFrame() {
        FinalFrame finalFrame = new FinalFrame(9);
        assertThat(finalFrame.sumOfScore()).isEqualTo(9);
        assertThat(finalFrame.scoreToSymbol()).isEqualTo("9");
        assertThat(finalFrame.isNext()).isEqualTo(false);

        finalFrame.addScore(1);
        assertThat(finalFrame.sumOfScore()).isEqualTo(10);
        assertThat(finalFrame.scoreToSymbol()).isEqualTo("9|/");
        assertThat(finalFrame.isNext()).isEqualTo(false);

        finalFrame.addScore(1);
        assertThat(finalFrame.sumOfScore()).isEqualTo(11);
        assertThat(finalFrame.scoreToSymbol()).isEqualTo("9|/|1");
        assertThat(finalFrame.isNext()).isEqualTo(true);
    }

    @DisplayName("FinalFrame 거터 및 2번 투구 테스트")
    @Test
    public void gutter_finalFrame() {
        FinalFrame finalFrame = new FinalFrame(9);
        assertThat(finalFrame.sumOfScore()).isEqualTo(9);
        assertThat(finalFrame.scoreToSymbol()).isEqualTo("9");
        assertThat(finalFrame.isNext()).isEqualTo(false);

        finalFrame.addScore(0);
        assertThat(finalFrame.sumOfScore()).isEqualTo(9);
        assertThat(finalFrame.scoreToSymbol()).isEqualTo("9|-");
        assertThat(finalFrame.isNext()).isEqualTo(true);
    }
}
