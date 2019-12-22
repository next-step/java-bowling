package bowling;

import bowling.domain.NormalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {

    @Test
    @DisplayName("객체 값 비교")
    void checkObjectValue() {
        NormalFrame normalFrame = new NormalFrame(0, 10);
        assertThat(normalFrame).isEqualTo(new NormalFrame(0, 10));
    }

    @Test
    @DisplayName("현재 점수 가져오기")
    void getCurrentScore() {
        NormalFrame normalFrame = new NormalFrame(0, 10);
        assertThat(normalFrame.getScore()).isEqualTo(10);
    }

    @Test
    @DisplayName("현재 프레임 인덱스 가져오기")
    void getCurrentFrameIndex() {
        NormalFrame normalFrame = new NormalFrame(0, 10);
        assertThat(normalFrame.getIndex()).isEqualTo(0);
    }
}
