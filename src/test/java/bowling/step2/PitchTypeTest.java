package bowling.step2;

import bowling.step2.domain.pitch.PitchType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PitchTypeTest {

    @Test
    @DisplayName("enum 값에 따라 출력되는 내용 달라지는지 확인")
    void convertTest() {
        assertThat(PitchType.STRIKE.convertToDisplay(10)).isEqualTo("X");
        assertThat(PitchType.SPARE.convertToDisplay(10)).isEqualTo("/");
        assertThat(PitchType.GUTTER.convertToDisplay(0)).isEqualTo("-");
    }
}
