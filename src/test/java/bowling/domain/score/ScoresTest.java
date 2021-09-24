package bowling.domain.score;

import bowling.domain.frame.Pitch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ScoresTest {

    private Scores scores;

    @BeforeEach
    void setUp() {
        scores = Scores.init();
    }

    @Test
    @DisplayName("현재 스코어의 상태가 PROGRESS 가 아니라면, 턴을 종료한다.")
    void turnOver() {
        assertAll(
                () -> {
                    scores.score(Pitch.from(3));
                    assertThat(scores.turnOver()).isFalse();
                },
                () -> {
                    scores.score(Pitch.from(3));
                    assertThat(scores.turnOver()).isTrue();
                },
                () -> {
                    scores.score(Pitch.from(10));
                    assertThat(scores.turnOver()).isTrue();
                }
        );
    }

}