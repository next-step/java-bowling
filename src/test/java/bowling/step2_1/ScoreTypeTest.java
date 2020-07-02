package bowling.step2_1;

import bowling.step2_1.domain.score.ScoreType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTypeTest {

    @Test
    @DisplayName("enum 값에 따라 출력되는 내용 달라지는지 확인")
    void convertTest() {
        assertThat(ScoreType.STRIKE.convertToDisplay(10)).isEqualTo("X");
        assertThat(ScoreType.SPARE.convertToDisplay(10)).isEqualTo("/");
        assertThat(ScoreType.GUTTER.convertToDisplay(0)).isEqualTo("-");
    }
}
