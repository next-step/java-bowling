package bowling.domain;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class FramesTest {

    @Test
    void 최초투구() {
        Frames frames = Frames.init();

        frames.bowling(10);
        Frame2 frame = frames.get(0);

        assertThat(frames.getCurrentRound()).isEqualTo(0);
        assertThat(frame.getResult(0)).isEqualTo(Result.STRIKE);
    }

    @Test
    void 다음투구() {
        Frames frames = Frames.init();

        frames.bowling(5);
        frames.bowling(5);
        Frame2 frame = frames.get(0);

        assertThat(frames.getCurrentRound()).isEqualTo(0);
        assertThat(frame.getResult(1)).isEqualTo(Result.SPARE);
    }

}
