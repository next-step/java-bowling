package bowling.constant;

import bowling.domain.pin.FinalFramePins;
import bowling.domain.pin.NormalFramePins;
import bowling.exception.NotFoundScoreException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoreTest {

    @Test
    @DisplayName("1차투구, 2차투구를 바탕으로 점수를 반환한다.")
    void score() {
        assertThat(Score.of(new NormalFramePins(10))).isEqualTo(Score.STRIKE);
        assertThat(Score.of(new FinalFramePins(10))).isEqualTo(Score.STRIKE);
        assertThat(Score.of(new NormalFramePins(2,8))).isEqualTo(Score.SPARE);
        assertThat(Score.of(new FinalFramePins(2,8))).isEqualTo(Score.SPARE);
        assertThat(Score.of(new NormalFramePins(0,0))).isEqualTo(Score.GUTTER);
        assertThat(Score.of(new FinalFramePins(0,0))).isEqualTo(Score.GUTTER);
    }

    @Test
    @DisplayName("찾을 수 없을 경우 예외를 반환한다.")
    void notFound() {
        assertThatThrownBy(() -> Score.of(new NormalFramePins(10, 6)))
                .isInstanceOf(NotFoundScoreException.class)
                .hasMessage("16 값을 가진 점수는 없습니다.");

        assertThatThrownBy(() -> Score.of(new FinalFramePins(10, 6)))
                .isInstanceOf(NotFoundScoreException.class)
                .hasMessage("16 값을 가진 점수는 없습니다.");
    }

}