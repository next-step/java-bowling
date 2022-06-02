package bowling.domain.state;

import bowling.domain.score.Score;
import bowling.domain.state.exception.BonusBowlingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class GutterTest {

    @Test
    @DisplayName("Gutter 클래스 객체 생성")
    void create() {
        assertThat(Ready.of(0).bowling(0)).isInstanceOf(Gutter.class);
    }

    @Test
    @DisplayName("이전 프레임 점수 계산 시, left 초기화")
    void calculateScore() {
        Score strikeScore = new Strike(10).createScore();
        Score currentScore = Ready.of(0).bowling(0).calculateScore(strikeScore);

        assertAll(() -> assertThat(currentScore.getScore()).isEqualTo(10),
                () -> assertThat(currentScore.left()).isEqualTo(0));
    }

    @Test
    @DisplayName("보너스 투구 시, 예외 처리")
    void bonusBowlingException() {
        assertThatThrownBy(() -> Ready.of(0).bowling(0).bonusBowling(0)).isExactlyInstanceOf(BonusBowlingException.class);
    }
}
