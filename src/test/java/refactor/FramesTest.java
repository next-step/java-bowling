package refactor;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {
    @Test
    void next는다음Frame을가리킨다() {
        Frame first = new FrameGeneral(new Scores(Arrays.asList(3, 4), 0), new Subtotal(State.INIT, 7));
        Frame second = new FrameGeneral(new Scores(Arrays.asList(2, 4), 0), new Subtotal(State.DONE, 16));
        Frames frames = new Frames(Arrays.asList(first, second));
        assertThat(frames.first()).isEqualTo(first);
        assertThat(frames.next(first)).isEqualTo(second);
    }

//    @Test
//    void 다음Frame은이전Frame의subtotal을더해야함() {
//        Frames frames = Frames.create();
//        Frame first = frames.first();
//        first = frames.play(first);
//        System.out.println(first);
//        Frame second = frames.next(first);
//        second = frames.play(second);
//        System.out.println(second);
//    }

    @Test
    void strike혹은spare인경우다음Frame을기다렸다가subtotal갱신한다() {
    }
}
