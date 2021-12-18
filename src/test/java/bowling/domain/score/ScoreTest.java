package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ScoreTest {

    public static Score score(int score) {
        return new Score(score);
    }

    public static Score score(int score, int bonusChance) {
        return new Score(score, bonusChance);
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

    @DisplayName("add:() 입력 점수의 score, bonusChance를 현재의 값에 각각 더한다.")
    @Test
    void add() {
        //given
        Score score = score(10, 0);

        //when
        score.add(score(10, 2));

        //then
        assertThat(score).isEqualTo(score(20, 2));
    }

    @DisplayName("addBonus(): 보너스 기회가 없다면 점수는 변하지 않는다.")
    @Test
    void addBonus_noBonusChance() {
        //given
        Score score = score(10, 0);

        //when
        score.addBonus(score(10));

        //then
        assertThat(score).isEqualTo(score(10));
    }

    @DisplayName("addBonus(): 보너스 기회가 있다면 남은 기회를 하나 감소시키고 점수를 더한다.")
    @Test
    void addBonus() {
        //given
        Score baseScore = score(10, 1);

        //when
        baseScore.addBonus(score(10));

        //then
        assertThat(baseScore).isEqualTo(score(20));
    }

    @DisplayName("addBonus(): 추가된 점수를 반환한다.")
    @ParameterizedTest(name = "[{index}] base: {0}, bonus: {1}, added: {2}")
    @MethodSource("addBonusReturnAddedScoreArguments")
    void addBonus_returnAddedScore(Score baseScore, Score bonusScore, Score addedScore) {
        assertThat(baseScore.addBonus(bonusScore)).isEqualTo(addedScore);
    }

    public static Stream<Arguments> addBonusReturnAddedScoreArguments() {
        return Stream.of(
                Arguments.of(score(10, 0), score(5), score(0)),
                Arguments.of(score(10, 1), score(5), score(5)),
                Arguments.of(score(10, 2), score(10), score(10))
        );
    }

    @DisplayName("보너스기회가 남았는데 점수를 구하면 예외를 던진다.")
    @Test
    void score_remainingBonusChance_throwsException() {
        Score score = score(0, 1);

        assertThat(score.canCalculate()).isFalse();
        assertThatThrownBy(score::value)
                .isInstanceOf(CanNotCalculateException.class);
    }

    @DisplayName("다음 프레임의 시작점수는 현재 프레임의 점수이고 보너스 기회는 없는 상태이다.")
    @Test
    void next() {
        Score nextScore = score(100, 1).next();
        assertThat(nextScore).isEqualTo(score(100));
    }

}
