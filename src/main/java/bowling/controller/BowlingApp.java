package bowling.controller;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.Player;
import bowling.view.Input;
import bowling.view.Output;

import java.util.stream.Stream;

public class BowlingApp {
    public static void main(String[] args) {
        String name = Input.scanPlayer();
        Player player = new Player(name);
        Frames frames = Frames.create();
        Output.printFrames(frames, player);
        playFrames(frames, player);
    }

    private static void playFrames(Frames frames, Player player) {
        Stream.iterate(0, i -> i < 10, i -> ++i)
                .forEach(i -> playAFrame(i, frames, player));
    }

    private static void playAFrame(int index, Frames frames, Player player) {
        Frame frame = frames.get(index);
        while (!frame.done()) {
            frame.pitchRandom(frames);
            Output.printFrames(frames, player);
        }
        if (frame.done() && index < 9) {
            frames.next(frame).updateNextSubtotal(frame.subtotal());
        }
    }
}
