package refactor;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

public class FrameLastTest {
    @Test
    void Last프레임은스트라이크이거나스페어이면한번을더투구할수있다() {
        Frames frames = Frames.create();
        Frame first = frames.first();
        Frame last = Stream.iterate(first, frame -> frames.next(frame))
                .limit(10).skip(9).findFirst().orElseThrow();
        last.pitchManual(10, frames);
        System.out.println(last);
    }
}
