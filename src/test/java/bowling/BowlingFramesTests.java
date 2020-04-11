package bowling;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("Bowling Frames Tests")
public class BowlingFramesTests {
    /*
     * BowlingFrames
     * BowlingFrame 목록을 관리한다.
     * 최종 프레임 리스트는 10개여야 한다.
     */

    @DisplayName("생성 테스트")
    @Test
    public void generateBowlingFramesTest() {
        assertThatCode(() -> BowlingFrames.newInstance()).doesNotThrowAnyException();
    }

    @DisplayName("사이즈 확인 테스트")
    @Test
    public void checkBowlingFramesSizeTest() {
        BowlingFrames bowlingFrames = BowlingFrames.newInstance();
        assertThat(bowlingFrames).size(10);
    }
}
