package refactor;

import java.util.stream.Stream;

public class BowlingApp {
    public static void main(String[] args) {
//        String name = Input.scan();
        Frames frames = Frames.create();
        playFrames(frames);
//        frames.play(0);
//        Output.printFrame(frames);
    }

    private static void playFrames(Frames frames) {
        Stream.iterate(0, i -> i < 10, i -> ++i)
                .forEach(i -> playAFrame(i, frames));
    }

    private static void playAFrame(int index, Frames frames) {
        Frame frame = frames.get(index);
        while (!frame.done()) {
            frame.pitchRandom(frames);
//            frame.pitch(10);
            Output.printFrames(frames);
        }
        if (frame.done() && index < 9) {
            frames.next(frame).updateNextSubtotal(frame.subtotal());
        }
    }
}
