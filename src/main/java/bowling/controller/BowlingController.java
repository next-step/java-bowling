package bowling.controller;

import bowling.domain.frame.Frames;

public class BowlingController {

    public static void game() {
        Frames frames = Frames.init();

        while (frames.isNext()) {
            frames.bowling(5);
            frames.bowling(5);
            if (frames.isBonus()) {
                frames.bowling(5);
            }
        }

        frames.print();
    }

    public static void main(String[] args) {
        game();
    }
}
