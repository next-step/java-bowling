package bowling.frame;

import bowling.pin.Pins;
import bowling.view.Output;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @Test
    void test(){
        Frames frames = new Frames();
        frames.pitch(new Pins(6));
        frames.pitch(new Pins(2));
        frames.pitch(new Pins(4));

        frames.getFrames().stream()
                .forEach(System.out::println);

        System.out.println(Output.getResultBody(frames));
    }

    @Test
    @DisplayName("프레임이 끝나지 않았을 때 점수를 구하면 -1을 반환한다.")
    void 끝나지않은프레임_점수생성예외(){
        Frames frames = new Frames();
        frames.pitch(new Pins(4));

        Frame frame = frames.getFrames().get(0);
        assertThat(frame.score()).isEqualTo(-1);
    }

    @Test
    @DisplayName("스트라이크 프레임에서 다음 프레임이 없을때 점수를 구하면 -1이 반환된다.")
    void strike_unfinished_next_frame_score_exception(){
        Frames frames = new Frames();
        frames.pitch(new Pins(10));
        frames.pitch(new Pins(2));

        Frame strike = frames.getFrames().get(0);
        assertThat(strike.score()).isEqualTo(-1);
    }


    @Test
    @DisplayName("스트라이크 프레임에서 점수를 구한다.")
    void strike_score(){
        Frames frames = new Frames();
        frames.pitch(new Pins(10));
        frames.pitch(new Pins(2));
        frames.pitch(new Pins(4));

        Frame strike = frames.getFrames().get(0);
        assertThat(strike.score()).isEqualTo(16);
    }

    @Test
    @DisplayName("스트라이크 프레임에서 점수를 구한다.")
    void strike_score2(){
        Frames frames = new Frames();
        frames.pitch(new Pins(10));
        frames.pitch(new Pins(10));
        frames.pitch(new Pins(10));

        Frame strike = frames.getFrames().get(0);
        assertThat(strike.score()).isEqualTo(30);
    }

    @Test
    @DisplayName("스페어 프레임에서 다음 프레임이 없을때 점수를 구하면 -1를 반환한다.")
    void spare_score2(){
        Frames frames = new Frames();
        frames.pitch(new Pins(4));
        frames.pitch(new Pins(6));

        Frame spare = frames.getFrames().get(0);

        assertThat(spare.score()).isEqualTo(-1);
    }

    @Test
    @DisplayName("스페어 프레임에서 점수를 구한다.")
    void spare_score(){
        Frames frames = new Frames();
        frames.pitch(new Pins(4));
        frames.pitch(new Pins(6));
        frames.pitch(new Pins(2));

        Frame spare = frames.getFrames().get(0);
        assertThat(spare.score()).isEqualTo(12);
    }


    @Test
    @DisplayName("미스 프레임에서 점수를 구한다.")
    void miss_점수(){
        Frames frames = new Frames();
        frames.pitch(new Pins(2));
        frames.pitch(new Pins(4));

        Frame spare = frames.getFrames().get(0);

        assertThat(spare.score()).isEqualTo(6);
    }

    @Test
    @DisplayName("gutter 프레임에서 점수를 구한다.")
    void gutter_점수(){
        Frames frames = new Frames();
        frames.pitch(new Pins(0));
        frames.pitch(new Pins(0));

        Frame spare = frames.getFrames().get(0);
        assertThat(spare.score()).isEqualTo(0);
    }
}