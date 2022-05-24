package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayFrameTest {
    @Test
    @DisplayName("playFrame은 value와 number를 가진다")
    void playFrameWithValueAndNumber() {
        PlayFrame playFrame =  new PlayFrame(1, "A");
        assertThat(playFrame.value()).isEqualTo("A");
        assertThat(playFrame.number()).isEqualTo(1);
    }
}
