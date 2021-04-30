package step4.domain.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step4.domain.Score;

import static org.assertj.core.api.BDDAssertions.then;

class StrikeTest {
    Strike strike;

    @BeforeEach
    void setUp() {
        strike = new Strike();
    }

    @Test
    @DisplayName("스트라이크 점수를 반환한다.")
    public void score() throws Exception {
        Score score = strike.score();
        then(score).isEqualTo(Score.Strike());
    }

    @Test
    @DisplayName("1번 이상 더할 수 있는 점수가 인자로 들어올 경우, 스트라이크 점수를 더한 점수를 반환한다. ")
    public void addScore() throws Exception {
        //given
        Score spare = Score.Spare();

        //when
        Score score = strike.addScore(spare);

        then(score.leftOpportunity()).isEqualTo(0);
        then(score.value()).isEqualTo(20);
    }

    @Test
    @DisplayName("1번 이상 더할 수 없는 점수가 인자로 들어올 경우, 인자 점수를 그대로 반환한다. ")
    public void addScore_same_score() throws Exception {
        //given
        Score miss = Score.Miss(10);

        //when
        Score score = strike.addScore(miss);

        then(score.value()).isEqualTo(10);
    }
}