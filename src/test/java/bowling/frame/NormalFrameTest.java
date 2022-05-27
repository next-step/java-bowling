package bowling.frame;

import bowling.domain.bowl.Bowl;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.pin.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    @Test
    @DisplayName("프레임의 첫번째 투구에서 모든 핀을 쓰러트렸을 때 테스트")
    void strike_pitch(){
        NormalFrame frame = new NormalFrame(1);
        Frame next = frame.pitch(new Pins(10));

    }

    @Test
    @DisplayName("프레임의 첫번째 투구에서 모든 핀을 쓰러트리지 못했을 때 테스트")
    void pitch(){
        NormalFrame frame = new NormalFrame(1);
        Frame next = frame.pitch(new Pins(8));

    }

    @Test
    @DisplayName("프레임의 두번째 투구에서 모든 핀을 쓰려트렸을 때")
    void spare_test(){
        NormalFrame first = new NormalFrame(1);
        Frame second = first.pitch(new Pins(8));
        Frame next = second.pitch(new Pins(2));

    }

    @Test
    @DisplayName("프레임의 두번째 투구에서도 모든 핀이 쓰려지지 않았을 때")
    void miss_test(){
        NormalFrame first = new NormalFrame(1);
        Frame second = first.pitch(new Pins(8));
        Frame next = second.pitch(new Pins(1));

    }

    @Test
    @DisplayName("핀을 하나도 쓰러트리지 못했을 때")
    void gutter_test(){
        NormalFrame first = new NormalFrame(1);
        Frame second = first.pitch(new Pins(0));
        Frame next = second.pitch(new Pins(0));


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
    @DisplayName("spare 프레임에서 전 프레임의 bowl점수에 보너스 점수 더해준다.")
    void next_score(){
        NormalFrame pre = new NormalFrame(1);
        pre.pitch(new Pins(10));

        NormalFrame cur = new NormalFrame(2);
        cur.pitch(new Pins(1));
        cur.pitch(new Pins(2));

        Bowl preBowl = pre.getBowl();
        assertThat(cur.calculateAdditionalScore(preBowl.score())).isEqualTo(13);
    }
}