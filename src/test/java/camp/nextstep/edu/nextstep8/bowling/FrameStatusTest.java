package camp.nextstep.edu.nextstep8.bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FrameStatusTest {
    @DisplayName("Frame status 가 제대로 심볼을 만드는지 확인")
    @Test
    public void frameStatusTest() {
        // given
        int score  = 1;
        int spare  = 2;

        // when & then
        assertAll(
                () -> assertThat(FrameStatus.STRIKE.makeSymbol(score,spare)).isEqualTo("X"),
                () -> assertThat(FrameStatus.SPARE.makeSymbol(score,spare)).isEqualTo(score + "|/"),
                () -> assertThat(FrameStatus.MISS.makeSymbol(score,spare)).isEqualTo(score + "|" + spare),
                () -> assertThat(FrameStatus.GUTTER.makeSymbol(score,spare)).isEqualTo("-")
        );
    }

}