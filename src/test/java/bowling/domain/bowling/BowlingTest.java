package bowling.domain.bowling;

import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("볼링 테스트")
public class BowlingTest {
    Bowling bowling = Bowling.of("ABC");

    @DisplayName("참여자 생성")
    @Test
    public void createMember() {

        assertThat(bowling.getName()).isEqualTo("ABC");
        assertThat(bowling.isFinished()).isEqualTo(false);
    }

    @DisplayName("현재 프레임")
    @Test
    public void currentFrame() {
        assertThat(bowling.getCurrentFrameNumber()).isEqualTo(1);
    }

    @DisplayName("투구")
    @Test
    public void throwBall() {
        bowling.throwBall(10);

        assertThat(bowling.getCurrentFrameNumber()).isEqualTo(2);
        assertThat(bowling.getFrames().get(0).getScores().get(0)).isEqualTo(Score.strike());
    }

    @DisplayName("점수 계산")
    @Test
    public void calculateScore() {
        bowling.throwBall(10);
        bowling.throwBall(1);
        bowling.throwBall(1);
        List<Integer> scores = bowling.calculateScores();

        assertThat(scores.get(0)).isEqualTo(12);
        assertThat(scores.get(1)).isEqualTo(14);
        for (int i = 0; i < 10; i++) {
            assertThat(scores.get(2)).isEqualTo(null);
        }
    }
}