package bowling.domain;

import bowling.exception.InvalidScoreException;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NormalFrameTest {
    @DisplayName("NormalFrame 생성 성공 테스트")
    @Test
    public void create_normalFrame() {
        NormalFrame normalFrame = new NormalFrame(3);
        assertThat(normalFrame).isEqualTo(new NormalFrame(3));
        assertThat(normalFrame.isNext()).isEqualTo(false);
    }

    @DisplayName("NormalFrame 생성 실패 테스트(핀 갯수 초과)")
    @Test
    public void create_normalFrame_error() {

        assertThatThrownBy(() -> new NormalFrame(11))
                .isInstanceOf(InvalidScoreException.class);
    }

    @DisplayName("NormalFrame 두번째 투구 실패 테스트(핀 갯수 초과)")
    @Test
    public void create_normalFrame_pitching_error() {
        NormalFrame normalFrame = new NormalFrame(3);
        assertThat(normalFrame.isNext()).isEqualTo(false);

        assertThatThrownBy(() -> normalFrame.addScore(8))
                .isInstanceOf(InvalidScoreException.class);
    }

    @DisplayName("NormalFrame 스트라이크 테스트")
    @Test
    public void strike_normalFrame() {
        NormalFrame normalFrame = new NormalFrame(10);
        assertThat(normalFrame.sumOfScore()).isEqualTo(10);
        assertThat(normalFrame.scoreToSymbol()).isEqualTo("X");
        assertThat(normalFrame.isNext()).isEqualTo(true);
    }

    @DisplayName("NormalFrame 스페어 테스트")
    @Test
    public void spare_normalFrame() {
        NormalFrame normalFrame = new NormalFrame(1);
        assertThat(normalFrame.sumOfScore()).isEqualTo(1);
        assertThat(normalFrame.isNext()).isEqualTo(false);

        normalFrame.addScore(9);
        assertThat(normalFrame.sumOfScore()).isEqualTo(10);
        assertThat(normalFrame.scoreToSymbol()).isEqualTo("1|/");
        assertThat(normalFrame.isNext()).isEqualTo(true);
    }

    @DisplayName("NormalFrame 거터 테스트")
    @Test
    public void gutter_normalFrame() {
        NormalFrame normalFrame = new NormalFrame(0);
        assertThat(normalFrame.sumOfScore()).isEqualTo(0);
        assertThat(normalFrame.isNext()).isEqualTo(false);
        assertThat(normalFrame.scoreToSymbol()).isEqualTo("-");

        normalFrame.addScore(0);
        assertThat(normalFrame.sumOfScore()).isEqualTo(0);
        assertThat(normalFrame.scoreToSymbol()).isEqualTo("-|-");
        assertThat(normalFrame.isNext()).isEqualTo(true);
    }
}
