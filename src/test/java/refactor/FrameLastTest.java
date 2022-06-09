package refactor;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameLastTest {
    @Test
    void Last프레임은스트라이크이거나스페어이면한번을더투구할수있다() {
        Frames frames = Frames.create();
//        Frame first = frames.first();
//        Frame last = Stream.iterate(first, frame -> frames.next(frame))
//                .limit(10).skip(9).findFirst().orElseThrow();
        Frame last = frames.get(9);
        last.pitchManual(10, frames);
        assertThat(last).isEqualTo(new
                FrameLast(
                new Scores(Arrays.asList(10), 1)
                , new Subtotal(State.INIT, 0)
        ));
    }

    @Test
    void lastFrame의State는INIT과DONE뿐이다() {
        Frames frames = Frames.create();
        Frame last = frames.get(9);
        last.pitchManual(6, frames);
        last.pitchManual(4, frames);
        last.pitchManual(0, frames);
        assertThat(last).isEqualTo(new
                FrameLast(
                new Scores(Arrays.asList(6, 4, 0), 0)
                , new Subtotal(State.DONE, 10)
        ));
    }
}
