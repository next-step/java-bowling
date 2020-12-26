package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class ScoreTest {

    @DisplayName("Score 생성 테스트")
    @Test
    void scoreConstructorTest(){

        Score score = Score.of(1);

        // then
        assertThat(score).isEqualTo(Score.of(1));
    }

    @DisplayName("Strike Score 생성 테스트")
    @Test
    void strikeScoreTest(){
        int strikeBonusCount = 2;
        Score score = Score.ofStrike(10);
        assertThat(score.getLeftBonusCount()).isEqualTo(strikeBonusCount);

        score.renewScore(10);
        assertThat(score.getLeftBonusCount()).isEqualTo(--strikeBonusCount);

        score.renewScore(1);
        assertThat(score.getLeftBonusCount()).isEqualTo(--strikeBonusCount);
    }

    @DisplayName("Spare Score 생성 테스트")
    @Test
    void spareScoreTest(){
        int spareBonusCount = 1;
        Score score = Score.ofSpare(10);
        assertThat(score.getLeftBonusCount()).isEqualTo(spareBonusCount);

        score.renewScore(0);
        assertThat(score.getLeftBonusCount()).isEqualTo(--spareBonusCount);
    }

    @DisplayName("남은 보너스 점수 획득 기회가 없을때 Exception")
    @Test
    void no_left_bonus_throw_exception(){
        assertThatIllegalArgumentException().isThrownBy(() -> {

            Score score = Score.ofSpare(10);
            score.renewScore(0);
            score.renewScore(2);

        }).withMessageContaining("남은 보너스 점수 획득 기회가 없습니다.");
    }

}
