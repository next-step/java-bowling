package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {

    @DisplayName("score 생성 테스트")
    @Test
    void create(){
        Frame frame = new NormalFrame(1);

        frame.bowl(Pins.of(8));
        frame.bowl(Pins.of(1));

        assertThat(frame.getScore()).isEqualTo(Score.of(9, 0));
    }

    @DisplayName("score 가 strike score 일때 score 계산의 남은 횟수가 감소하는지 테스트")
    @Test
    void strike(){
        Score strikeScore = Score.of(10,2);

        assertThat(strikeScore.additionalScore(Pins.of(4))).isEqualTo(Score.of(14, 1));
    }

    @DisplayName("score 가 spare score 일때 score 계산의 남은 횟수 0 테스트")
    @Test
    void spare(){
        Score spare = Score.of(10,0);

        assertThat(spare.additionalScore(Pins.of(4))).isEqualTo(Score.of(14, 0));
    }
}
