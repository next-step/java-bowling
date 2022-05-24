package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayFramesTest {
    @Test
    @DisplayName("playFrames() 는 담고있는 PlayFrame 개수만큼 return한다")
    void name() {
        PlayFrames playFrames = new PlayFrames();
        playFrames.add(new PlayFrame(1, "a"));
        playFrames.add(new PlayFrame(2, "b"));
        assertThat(playFrames.playFrames()).hasSize(2);
    }
}
