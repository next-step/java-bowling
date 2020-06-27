package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class FramesTest {

    @DisplayName("10개의 프레임 목록을 가진다")
    @Test
    void create() {
        Frames frames = Frames.create();
        List<Frame> frameList = frames.getContent();

        assertThat(frameList.size()).isEqualTo(10);
    }

    @DisplayName("마지막 프레임은 FinalFrame 인스턴스다")
    @Test
    void finalFrame() {
        Frames frames = Frames.create();
        List<Frame> frameList = frames.getContent();

        assertThat(frameList.get(frameList.size() - 1)).isInstanceOf(FinalFrame.class);
    }
}
