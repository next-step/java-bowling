package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {
    private Frames frames;

    @BeforeEach
    void setUp() {
        frames = new Frames();
    }
    @DisplayName("프레임에 쓰러트린 핀 갯수를 저장할 수 있다")
    @Test
    void init() {
        frames.add(2);
    }

    @DisplayName("핀 갯수 저장 후 현재 프레임이 끝났다면, 다음 프레임으로 넘어간다")
    @Test
    void nextFrame() {
        frames.add(10);
        assertThat(frames.getCurrentFrame()).isEqualTo(1);
    }

    @DisplayName("현재 프레임이 마지막인지 여부를 체크한다")
    @Test
    void lastFrame(){
        int dummyPinCount = 10;
        for(int i = 0; i < 8; i++) {
            frames.add(dummyPinCount);
        }
        assertThat(frames.isLast()).isFalse();
        frames.add(dummyPinCount);
        assertThat(frames.isLast()).isTrue();
    }
}
