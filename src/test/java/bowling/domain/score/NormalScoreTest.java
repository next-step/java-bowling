package bowling.domain.score;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NormalScoreTest {

    @Test
    @DisplayName("빈 NormalScore 보드를 생성할 수 있다.")
    void createEmptyNormalScoreTest() {

        // when
        NormalScore result = NormalScore.empty();

        // then
        assertThat(result).isInstanceOf(NormalScore.class);
    }

}