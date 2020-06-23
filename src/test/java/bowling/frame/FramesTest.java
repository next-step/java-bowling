package bowling.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @DisplayName("initiate하면 자동으로 NormalFrame을 내부 컬렉션에 추가함")
    @Test
    public void initiate_정상() {
        Frames frames = Frames.initiate();
        List<Frame> frameList = frames.getFrames();

        assertThat(frameList.size()).isEqualTo(1);
        assertThat(frameList.get(0).getIndex()).isEqualTo(1);
    }

}
