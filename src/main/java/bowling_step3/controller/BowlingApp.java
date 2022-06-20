package bowling_step3.controller;

import bowling_step3.domain.*;
import bowling_step3.view.Output;

import java.util.stream.Stream;

public class BowlingApp {
    public static void main(String[] args) {
//        String name = Input.scanPlayer();
        Player player = new Player("tst");

        Frames frames = Frames.create();
        Frame frame =frames.first();
        while (!(frame.next() == null && frame.done())) {
//            frame = frame.playManual(5);
//            Scores scores = frame.scores().pitch(10);
            Scores scores = frame.scores().pitchRandom();
            frame = frame.play(scores);
            Subtotals subtotals = frames.first().createSubtotals();
            Output.printFrames(10, frames, player);
            Output.printSubtotals(subtotals, player);
        }
    }

    private static void playFrames(Frames frames, Player player) {
        Stream.iterate(0, i -> i < 10, i -> ++i)
                .forEach(i -> playAFrame(i, frames, player));
    }

    private static void playAFrame(int index, Frames frames, Player player) {
        Frame frame = frames.first();

        frame = frame.play(10);
//        Frame frame = frames.get(index);
//        while (!frame.done()) {
//            frame = frame.playManual(10);
//            Output.printFrames(index+1, frames, player);
//        }
//        if (frame.done() && index < 9) {
//            frames.next(frame).updateNextSubtotal(frame.subtotal());
//        }
    }
}
