package bowling_step3.controller;

import bowling_step3.domain.*;
import bowling_step3.view.Input;
import bowling_step3.view.Output;

import java.util.stream.Stream;

public class BowlingApp {
    public static void main(String[] args) {
        String name = Input.scanPlayer();
        Player player = new Player(name);

        Frames frames = Frames.create();
        Frame frame =frames.first();
        while (!(frame.next() == null && frame.done())) {
            int randomPin = frame.scores().getRandom();
            frame = frame.play(randomPin);
            Subtotals subtotals = frames.first().createSubtotals();
            Output.printFrames(10, frames, player);
            Output.printSubtotals(subtotals, player);
        }
    }
}
