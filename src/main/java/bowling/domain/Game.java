package bowling.domain;

import bowling.View;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Game() { }

    public static void play(View view) {
        String playerName = view.getName();

        FrameSet frameSet = FrameSet.create(1);
        List<FrameSet> playResults = new ArrayList<>();

        while (!frameSet.isEnd()) {
            int hitCountText = view.getHitCount(frameSet.getPlayCount());
            frameSet.play(hitCountText);

            playResults.add(frameSet.snapShot());
            view.showFrameSetResult(playerName, playResults);

            frameSet = frameSet.readyNext();
        }
    }
}
