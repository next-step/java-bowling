package bowling.utils;

import bowling.domain.BowlingGame;
import bowling.domain.BowlingGameFrame;
import bowling.domain.FinalBowlingGameFrame;
import bowling.domain.NormalBowlingGameFrame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BowlingGameGenerator {

    public static BowlingGame createBowlingGame() {
        List<BowlingGameFrame> frames = new ArrayList<>();
        IntStream.range(0, BowlingGame.SIZE_OF_FRAMES - 1)
                .forEach(i -> frames.add(new NormalBowlingGameFrame()));
        frames.add(new FinalBowlingGameFrame());
        return new BowlingGame(frames);
    }

}
