package bowling.domain.engine.frame;

import bowling.domain.concrete.FinalFrame;
import bowling.domain.concrete.NormalFrame;
import bowling.domain.engine.PitchResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    static Frames allFinishedFrames() {
        Frames frames = Frames.initialize(new NormalFrame());
        frames.getLast().throwBall(PitchResult.wrap(10));

        for (int i = 0; i < 8; i++){
            frames.add(new NormalFrame());
            frames.getLast().throwBall(PitchResult.wrap(10));
        }

        frames.add(new FinalFrame());
        frames.getLast().throwBall(PitchResult.wrap(7));
        frames.getLast().throwBall(PitchResult.wrap(2));


        return frames;
    }

    @Test
    @DisplayName("다음 프레임의 번호를 가져온다.")
    void getNextFrameNumber() {
        Frames frames = Frames.initialize(new NormalFrame());
        assertThat(frames.getNextFrameNumber()).isEqualTo(1);

        frames.getLast().throwBall(PitchResult.wrap(10));

        assertThat(frames.getNextFrameNumber()).isEqualTo(2);
    }

    @Test
    @DisplayName("볼링 게임이 종료된 상태에서 다음 번호를 요청하면 -1 을 반환한다.")
    void returnMinusIfGameIsEnded() {
        assertThat(allFinishedFrames().getNextFrameNumber()).isEqualTo(-1);
    }

    @Test
    @DisplayName("마지막 프레임을 가져온다.")
    void getLastFrame() {
        Frame firstFrame = new NormalFrame();
        Frames frames = Frames.initialize(firstFrame);

        assertThat(frames.getLast()).isSameAs(firstFrame);
    }

    @Test
    @DisplayName("모든 프레임이 종료되면 true 를 반환한다.")
    void returnTrueIfAllFrameIsEnded() {
        assertThat(allFinishedFrames().isAllFrameEnded()).isTrue();
    }
    
}
