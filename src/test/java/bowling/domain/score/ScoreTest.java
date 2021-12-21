package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ScoreTest {

    public static Score score(int score) {
        return new Score(score);
    }

    public static Score score(int score, int bonusChance) {
        return new Score(score, bonusChance);
    }

    public static Score score(int score, Score nextScore, int bonusChance) {
        return new Score(score, nextScore, bonusChance);
    }

    @DisplayName("점수가 음수라면 예외를 던진다.")
    @Test
    void create_negativeScore() {
        assertThatThrownBy(() -> new Score(-1))
                .isInstanceOf(IllegalScoreException.class)
                .hasMessage("점수가 음수일 수 없습니다.");
    }

    @DisplayName("보너스 기회가 음수라면 예외를 던진다.")
    @Test
    void create_negativeBonusChance() {
        assertThatThrownBy(() -> new Score(0, - 1))
                .isInstanceOf(IllegalScoreException.class)
                .hasMessage("보너스 기회가 음수일 수 없습니다.");
    }

    @DisplayName("현재 점수에 입력 점수를 더하고, 현재 보너스 기회를 입력 보너스 기회로 바꾼다")
    @Test
    void add() {
        //given
        Score score = score(10, 0);

        //when
        score.add(score(10, 2));

        //then
        assertThat(score).isEqualTo(score(20, 2));
    }

    @DisplayName("보너스 기회가 있다면 남은 기회를 하나 감소시키고 점수를 더한다.")
    @Test
    void addPlus() {
        //given
        Score baseScore = score(10, 1);

        //when
        baseScore.addBonus(score(10));

        //then
        assertThat(baseScore).isEqualTo(score(20, 0));
    }

    @DisplayName("보너스기회가 남았는데 점수를 구하면 예외를 던진다.")
    @Test
    void score_remainingBonusChance_throwsException() {
        Score score = score(0, 1);

        assertThat(score.canCalculate()).isFalse();
        assertThatThrownBy(score::value)
                .isInstanceOf(CanNotCalculateException.class);
    }

    @DisplayName("보너스 기회가 있다면 점수를 더하고 모든 다음 점수에도 반영한다.")
    @Test
    void addBonus() {
        //given
        Score thirdScore = score(40, 1);
        Score secondScore = score(30, thirdScore, 2);
        Score firstScore = score(20, secondScore, 2);

        //when
        firstScore.addBonus(score(10));
        firstScore.addBonus(score(5));

        //then
        assertThat(thirdScore).isEqualTo(score(55, 1));
        assertThat(secondScore).isEqualTo(score(45, thirdScore, 2));
        assertThat(firstScore).isEqualTo(score(35, secondScore, 0));
    }

    @DisplayName("보너스 기회가 없다면 현재점수, 다음점수 모두 변하지 않는다.")
    @Test
    void addBonus_noLeftChance() {
        //given
        Score thirdScore = score(40, 1);
        Score secondScore = score(30, thirdScore, 2);
        Score firstScore = score(20, secondScore, 0);

        //when
        firstScore.addBonus(score(10));

        //then
        assertThat(thirdScore).isEqualTo(score(40, 1));
        assertThat(secondScore).isEqualTo(score(30, thirdScore, 2));
        assertThat(firstScore).isEqualTo(score(20, secondScore, 0));
    }

}
