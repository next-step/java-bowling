package bowling.domain.score;

import bowling.exception.ScoreListNullPointerException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoresTest {

    @DisplayName("Scores 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // given
        List<Score> scoreList = new ArrayList<>();

        // when
        Scores scores = new Scores(scoreList);

        // then
        assertThat(scores).isNotNull();
    }

    @DisplayName("Scores 인스턴스 생성시 null 입력 여부 테스트")
    @Test
    void 검증_null() {
        // given
        List<Score> scoreList = null;

        // when and then
        assertThatThrownBy(() -> new Scores(scoreList))
                .isInstanceOf(ScoreListNullPointerException.class)
                .hasMessage("List<Score> 인스턴스가 null 입니다.");
    }

    @DisplayName("Scores 인스턴스가 합계를 반환하는지 테스트")
    @Test
    void 반환_합계() {
        // given
        List<Score> scoreList = new ArrayList<>(Arrays.asList(Score.miss(5), Score.spare(), Score.strike()));

        // when
        Scores scores = new Scores(scoreList);

        // then
        assertThat(scores.sum()).isEqualTo(25);
    }

    @DisplayName("Scores 인스턴스가 소유한 자료들의 갯수를 반환하는지 테스트")
    @Test
    void 반환_사이즈() {
        // given
        List<Score> scoreList = new ArrayList<>(Arrays.asList(Score.miss(5), Score.spare(), Score.strike()));

        // when
        Scores scores = new Scores(scoreList);

        // then
        assertThat(scores.size()).isEqualTo(3);
    }

}