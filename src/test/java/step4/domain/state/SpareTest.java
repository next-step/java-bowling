package step4.domain.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step4.domain.PinCount;
import step4.domain.Score;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.BDDAssertions.then;

class SpareTest {
    Spare spare;

    @BeforeEach
    void setUp() {
        spare = new Spare(new PinCount(5), new PinCount(5));
    }

    @Test
    @DisplayName("두 쓰러뜨린 인자의 합이 10이 아닐 경우, 예외가 발생한다.")
    public void create_exception() throws Exception {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Spare(new PinCount(4), new PinCount(5)))
                .withMessage("두번에 걸쳐 쓰러뜨린 핀의 개수가 10이 아닙니다.");
    }

    @Test
    @DisplayName("스페어 점수를 반환한다.")
    public void score() throws Exception {
        Score score = spare.score();
        then(score).isEqualTo(Score.Spare());
    }

    @Test
    @DisplayName("1번 더할 수 있는 점수가 인자로 들어올 경우, 스페어 점수를 더한 점수를 반환한다. ")
    public void addScore_one_opportunity() throws Exception {
        //given
        Score spare = Score.Spare();

        //when
        Score score = this.spare.addScore(spare);

        then(score.leftOpportunity()).isEqualTo(0);
        then(score.value()).isEqualTo(15);
    }

    @Test
    @DisplayName("2번 더할 수 있는 점수가 인자로 들어올 경우, 스페어 점수를 더한 점수를 반환한다. ")
    public void addScore_second_opportunity() throws Exception {
        //given
        Score strike = Score.Strike();

        //when
        Score score = spare.addScore(strike);

        then(score.leftOpportunity()).isEqualTo(0);
        then(score.value()).isEqualTo(20);
    }

    @Test
    @DisplayName("1번 이상 더할 수 없는 점수가 인자로 들어올 경우, 인자 점수를 그대로 반환한다. ")
    public void addScore_same_score() throws Exception {
        //given
        Score miss = Score.Miss(10);

        //when
        Score score = spare.addScore(miss);

        then(score.value()).isEqualTo(10);
    }
}