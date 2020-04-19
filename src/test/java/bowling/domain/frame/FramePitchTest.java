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

    @DisplayName("첫번째 투구가 스트라이크인지 체크한다.")
    @Test
    void checkFirst() {
        FramePitch strikePitch = new FramePitch();
        strikePitch.add(10);
        assertThat(strikePitch.isFirstPitchStrike()).isTrue();

        FramePitch normalPitch = new FramePitch();
        normalPitch.add(9);
        assertThat(normalPitch.isFirstPitchStrike()).isFalse();
    }

    @DisplayName("두번째 투구의 점수를 구할 수 있다.")
    @Test
    void getSecond() {
        FramePitch framePitch = new FramePitch();
        framePitch.add(1);
        framePitch.add(2);
        Optional<Score> secondScore = framePitch.getSecondPitchScore();
        assertThat(secondScore.isPresent()).isTrue();
        assertThat(secondScore.get().getScore()).isEqualTo(2);
    }

    @DisplayName("두번째 투구가 스페어인지 체크한다.")
    @Test
    void checkSecond() {
        FramePitch strikePitch = new FramePitch();
        strikePitch.add(1);
        strikePitch.add(9);
        assertThat(strikePitch.isSecondPitchSpare()).isTrue();

        FramePitch normalPitch = new FramePitch();
        normalPitch.add(1);
        normalPitch.add(8);
        assertThat(normalPitch.isSecondPitchSpare()).isFalse();
    }

}
