package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {

    @DisplayName("정적 팩토리 메서드 사용 시 사이즈 1")
    @Test
    void sizeTest() {
        Frames frames = Frames.newInstance();

        assertThat(frames.size()).isEqualTo(1);
    }

    @DisplayName("Next Frame 상황이 아니면 delete 후 add -> size 변화 없음")
    @Test
    void addTest() {
        Frames frames = Frames.newInstance();

        Frame current = frames.currentFrame();
        current.bowl(Pins.of(3));

        frames.add(current);

        assertThat(frames.size()).isEqualTo(1);
    }

    @DisplayName("Next Frame 으로 넘어 갈 상황이면 add -> size + 1")
    @Test
    void addNextFrameTest() {
        Frames frames = Frames.newInstance();

        Frame prevFrame = frames.currentFrame();
        prevFrame.bowl(Pins.of(3));
        prevFrame.bowl(Pins.of(3));

        Frame nextFrame = prevFrame.next();
        frames.add(nextFrame);

        assertThat(frames.size()).isEqualTo(2);
    }
}
