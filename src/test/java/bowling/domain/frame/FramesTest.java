package bowling.domain.frame;

import bowling.domain.state.Finished;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FramesTest {
    @Test
    @DisplayName("초기화 시 한개의 프레임이 생성된다.")
    void init() {
        Frames frames = Frames.init();
        assertThat(frames).isNotNull();
        assertThat(frames.getFrames().size()).isOne();
    }

    @Test
    @DisplayName("Frames의 최근 Frame을 가져올 수 있다.")
    void lastFrame() {
        Frame firstFrame = NormalFrame.init(1);
        Frame secondFrame = NormalFrame.init(2);
        Frames frames = new Frames(Arrays.asList(firstFrame, secondFrame));
        assertThat(frames.lastFrame()).isEqualTo(secondFrame);
    }

    @Test
    @DisplayName("Frames의 종료 시점은 마지막 프레임(FinalFrame)이 종료되었을 때이다.")
    void endFrames() {
        Frames finishedFinalFrame = mockFinishedFinalFrame();
        assertThat(finishedFinalFrame.isFinish()).isTrue();
    }

    @Test
    @DisplayName("종료된 Frames에 볼을 던질 수 없다.")
    void endFramesCantBowl() {
        Frames finishedFinalFrame = mockFinishedFinalFrame();
        assertThrows(IllegalStateException.class, () -> finishedFinalFrame.bowl(() -> 10));
    }

    @Test
    @DisplayName("최근 Frame이 완료되지 않은 경우 최근 Frame에 볼을 굴린다.")
    void bowlCurrentFrame() {
        Frames firstFrame = Frames.init();
        Frames newFrames = firstFrame.bowl(() -> 5).bowl(() -> 3);
        assertThat(newFrames.lastFrame()).isEqualTo(NormalFrame.init(1));
        assertThat(newFrames.lastFrame().isFinished()).isTrue();
    }

    @Test
    @DisplayName("최근 Frame이 완료 된 경우 다음 Frame에 볼을 굴린다.")
    void bowlNextFrame() {
        Frames firstFrame = Frames.init();
        Frames newFrames = firstFrame.bowl(() -> 5).bowl(() -> 3).bowl(() -> 5);
        assertThat(newFrames.lastFrame()).isEqualTo(NormalFrame.init(2));
        assertThat(newFrames.lastFrame().isFinished()).isFalse();
    }

    private Frames mockFinishedFinalFrame() {
        return new Frames(Collections.singletonList(new FinalFrame(10, new Finished() {
            @Override
            public String print() {
                return null;
            }
        })));
    }
}
