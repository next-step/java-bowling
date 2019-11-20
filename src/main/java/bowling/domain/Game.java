package bowling.domain;

import bowling.View;

public class Game {

    private Game() { }

    public static void play(View view) {
        String playerName = view.getName();

        FrameSet frameSet = FrameSet.create(1);
        FrameResults frameResults = new FrameResults();

        while (!frameSet.isEnd()) {
            int hitCountText = view.getHitCount(frameSet.getPlayCount());
            frameSet.play(hitCountText);

            frameResults.add(frameSet.snapShot());
            view.showFrameSetResult(playerName, frameResults.getValue());

            frameSet = frameSet.readyNext();
        }
    }
}
