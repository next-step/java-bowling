package bowling.domain.engine.frame;

import bowling.domain.RollResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    static Frames allFinishedFrames() {
        Frames frames = Frames.init();
        frames.roll(RollResult.of(10));

        for (int i = 0; i < 8; i++){
            frames.roll(RollResult.of(10));
        }

        frames.roll(RollResult.of(7));
        frames.roll(RollResult.of(2));

        return frames;
    }

    @Test
    @DisplayName("다음 프레임의 번호를 가져온다.")
    void getNextFrameNumber() {
        Frames frames = Frames.init();
        assertThat(frames.getNextFrameNumber()).isEqualTo(1);

        frames.roll(RollResult.of(10));

        assertThat(frames.getNextFrameNumber()).isEqualTo(2);
    }
    
    @Test
    @DisplayName("모든 프레임이 종료되면 true 를 반환한다.")
    void returnTrueIfAllFrameIsEnded() {
        assertThat(allFinishedFrames().isAllFrameEnded()).isTrue();
    }

}
