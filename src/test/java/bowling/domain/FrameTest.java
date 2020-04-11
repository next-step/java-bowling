package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class FrameTest {
    @DisplayName("1프레임 생성")
    @Test
    void createFrame() {
        assertThatCode(() -> Frame.first()).doesNotThrowAnyException();
    }

    @DisplayName("다음 프레임 생성")
    @Test
    void createNextFrame() {
        Frame first = Frame.first();
        assertThatCode(() -> first.nextFrame()).doesNotThrowAnyException();
    }

    @DisplayName("점수 추가")
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6,7,8,9,10})
    void createNextFrame(int score) {
        Frame frame = Frame.first();
        frame.addScore(score);

        assertThat(frame.getScore()).isEqualTo(score);
    }
}