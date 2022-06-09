package refactor;

import java.util.stream.Stream;

public class BowlingApp {
    public static void main(String[] args) {
        Frames frames = Frames.create();
        playFrames(frames);
    }

    private static void playFrames(Frames frames) {
        Stream.iterate(0, i -> i < 10, i -> ++i)
                .forEach(i -> playAFrame(i, frames));
    }

    private static void playAFrame(int index, Frames frames) {
        Frame frame = frames.get(index);
        while (!frame.done()) {
            frame.pitchRandom(frames);
            Output.printFrames(frames);
        }
        if (frame.done() && index < 9) {
            frames.next(frame).updateNextSubtotal(frame.subtotal());
        }
    }
}
