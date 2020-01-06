package bowling.controller;

import bowling.View;
import bowling.domain.FrameResults;
import bowling.domain.player.Player;
import bowling.domain.set.FrameSets;

public class Game {

    private Game() {
    }

    public static void play(View view) {
        Player player = Player.create(view.getName());
        FrameSets frameSets = FrameSets.create();

        while (frameSets.hasNext()) {
            int hitCount = view.getHitCount(frameSets.getCurrentPlayCount());
            FrameResults frameResults = frameSets.play(hitCount);

            view.showFrameSetResult(player.getName(), frameResults);
        }
    }
}
