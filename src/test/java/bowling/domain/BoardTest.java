package bowling.domain;

import bowling.domain.result.GameResultTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    @DisplayName("정상 생성 테스트")
    @Test
    void createTest() {
        assertThat(new Board(GameResultTest.MIZ_STRIKE_AND_FIVE))
                .isEqualTo(new Board(GameResultTest.MIZ_STRIKE_AND_FIVE));
    }


}
