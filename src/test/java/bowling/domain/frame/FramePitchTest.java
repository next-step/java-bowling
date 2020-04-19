package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class FramePitchTest {
    @DisplayName("점수를 구할 수 있다.")
    @Test
    void getTotal() {
        FramePitch framePitch = new FramePitch();
        framePitch.add(8);
        framePitch.add(1);
        assertThat(framePitch.getScore().getScore()).isEqualTo(9);
    }

    @DisplayName("첫번째 투구의 점수를 구할 수 있다.")
    @Test
    void getFirst() {
        FramePitch framePitch = new FramePitch();
        framePitch.add(1);
        Optional<Score> firstScore = framePitch.getFirstPitchScore();
        assertThat(firstScore.isPresent()).isTrue();
        assertThat(firstScore.get().getScore()).isEqualTo(1);
    }
}
