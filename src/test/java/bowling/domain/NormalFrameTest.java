package bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {

    @DisplayName("프레임생성 테스트")
    @Test
    public void testCreate() {
        NormalFrame normalFrame = NormalFrame.firstFrame();
        assertThat(normalFrame.getIndex()).isEqualTo(1);
    }

    @DisplayName("새로운 프레임생성 테스트")
    @Test
    public void testNextFrame() {
        NormalFrame firstFrame = NormalFrame.firstFrame();
        assertThat(firstFrame.nextFrame().getIndex()).isEqualTo(2);
    }

    @DisplayName("점수 추가")
    @ParameterizedTest
    @ValueSource(ints = {10, 8, 2, 7})
    void createNextFrame(int score) {
        NormalFrame firstFrame = NormalFrame.firstFrame();
        firstFrame.sumScore(score);

        assertThat(firstFrame.getScore()).isEqualTo(score);
    }


}
