package bowling.controller;

import bowling.View;
import bowling.domain.FrameResults;
import bowling.domain.Player;
import bowling.domain.set.FrameSet;
import bowling.domain.set.NormalFrameSet;

public class Game {

    private Game() {
    }

    public static void play(View view) {
        Player player = Player.create(view.getName());

        FrameSet frameSet = NormalFrameSet.createFirst();
        FrameResults frameResults = new FrameResults();

        while (!frameSet.isEnd()) {
            int hitCount = view.getHitCount(frameSet.getPlayCount());
            frameSet.play(hitCount);

            frameResults.add(frameSet.snapShot());
            view.showFrameSetResult(player.getName(), frameResults.getValue());

            frameSet = frameSet.next();
        }
    }
}
