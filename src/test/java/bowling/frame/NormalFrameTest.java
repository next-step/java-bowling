package bowling.frame;

import bowling.domain.bowl.Bowl;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.pin.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    Frame previousFrame;
    Bowl sprae;

    @BeforeEach
    void before(){
        previousFrame = new NormalFrame(9);
        previousFrame.pitch(new Pins(1));
        previousFrame.pitch(new Pins(9));
        sprae = previousFrame.getBowls().get(0);
    }

    @Test
    @DisplayName("프레임이 끝나지 않았을 때 점수를 구하면 -1을 반환한다.")
    void unfinished_score_exception(){
        NormalFrame frame = new NormalFrame(1);
        frame.pitch(new Pins(3));

        assertThat(frame.score()).isEqualTo(-1);
    }

    @Test
    @DisplayName("miss일때 점수 구하기")
    void miss_score(){
        NormalFrame frame = new NormalFrame(1);
        frame.pitch(new Pins(3));
        frame.pitch(new Pins(3));
        assertThat(frame.score()).isEqualTo(6);
    }

    @Test
    @DisplayName("gutter 점수 구하기")
    void gutter_score(){
        NormalFrame frame = new NormalFrame(1);
        frame.pitch(new Pins(0));
        frame.pitch(new Pins(0));
        assertThat(frame.score()).isEqualTo(0);
    }

    @Test
    @DisplayName("프레임에서 전 프레임의 bowl점수에 보너스 점수 더해준다.")
    void next_score(){
        NormalFrame cur = new NormalFrame(2);
        cur.pitch(new Pins(2));
        cur.pitch(new Pins(3));

        assertThat(cur.calculateAdditionalScore(sprae.score())).isEqualTo(12);
    }
}