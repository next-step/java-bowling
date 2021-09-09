package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {

    @DisplayName("score 생성 테스트")
    @Test
    void create() {
        Frame frame = new NormalFrame(1);

        frame.bowl(Pins.of(8));
        frame.bowl(Pins.of(1));

        assertThat(frame.getScore()).isEqualTo(Score.of(9, 0));
    }

    @DisplayName("score 가 strike score 일때 score 계산의 남은 횟수가 감소하는지 테스트")
    @Test
    void strike() {
        Score strikeScore = Score.of(10, 2);

        assertThat(strikeScore.additionalScore(Pins.of(4))).isEqualTo(Score.of(14, 1));
    }

    @DisplayName("score 가 spare score 일때 score 계산의 남은 횟수 0 테스트")
    @Test
    void spare() {
        Score spare = Score.of(10, 0);

        assertThat(spare.additionalScore(Pins.of(4))).isEqualTo(Score.of(14, 0));
    }

    @DisplayName("마지막 프레임 점수 합산 테스트")
    @Test
    void finalFrame() {
        NormalFrame frame = new NormalFrame(9);
        frame.bowl(Pins.of(9));
        frame.bowl(Pins.of(1));

        Frame finalFrame = frame.next();

        finalFrame.bowl(Pins.of(7));

        assertThat(frame.getScore()).isEqualTo(Score.of(17, 0));
    }

    @DisplayName("score 가 triple strike 일때는 세번째 strike 시 첫번째 score 에 30으로 계산")
    @Test
    void tripleStrikeFrame() {
        NormalFrame frame = new NormalFrame(1);
        frame.bowl(Pins.of(10));
        Frame secondFrame = frame.next();
        secondFrame.bowl(Pins.of(10));
        Frame thirdFrame = secondFrame.next();
        thirdFrame.bowl(Pins.of(10));

        assertThat(frame.getScore()).isEqualTo(Score.of(30, 0));
    }

}
