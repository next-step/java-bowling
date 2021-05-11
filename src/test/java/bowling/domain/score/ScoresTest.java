package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ScoresTest {

    @DisplayName("Scores 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // given
        List<Score> scoreList = new ArrayList<>();

        // when
        Scores scores = Scores.of(scoreList);

        // then
        assertThat(scores).isNotNull();
    }

    @DisplayName("Scores 인스턴스 생성시 null 입력 여부 테스트")
    @Test
    void 검증_null() {
        // given
        List<Score> scoreList = null;

        // when and then
        assertThatThrownBy(()->Scores.of(scoreList))
                .isInstanceOf(ScoreListNullPointerException.class)
                .hasMessage("List<Score> 가 null 입니다.");
    }
}