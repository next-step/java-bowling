package bowling.domain.frame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static bowling.domain.frame.Frames.createFrames;
import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {
    private Frames frames;

    @BeforeEach
    void setUp() {
        frames = createFrames();
    }

    @DisplayName("프레임 추가 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void addTest(int size) {
        for (int i = 0; i < size; i++) {
            frames.add(DefaultFrame.firstFrame());
        }
        assertThat(frames.size()).isEqualTo(size);
    }


    @DisplayName("프레임 추가 예외처리 테스트")
    @Test
    void addFailedTest() {
        for (int i = 0; i < 10; i++) {
            frames.add(DefaultFrame.firstFrame());
        }
        assertThat(frames.isOver()).isTrue();
    }
}
