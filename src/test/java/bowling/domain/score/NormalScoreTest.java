package bowling.domain.score;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NormalScoreTest {

    @Test
    @DisplayName("빈 socre를 생성할 수 있다.")
    void createEmptyScoreTest() {

        // given & when
        Score result = NormalScore.emptyScore();

        // then
        assertAll(
            () -> assertThat(result).isInstanceOf(Score.class),
            () -> assertThat(result).isInstanceOf(NormalScore.class)
        );
    }

}