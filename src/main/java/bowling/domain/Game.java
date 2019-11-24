package bowling.domain;

import bowling.View;

public class Game {

    private Game() {
    }

    public static void play(View view) {
        String playerName = view.getName();

        FrameSet frameSet = NormalFrameSet.createFirst();
        FrameResults frameResults = new FrameResults();

        while (!frameSet.isEnd()) {
            int hitCount = view.getHitCount(frameSet.getPlayCount());
            frameSet.play(hitCount);

            frameResults.add(frameSet.snapShot());
            view.showFrameSetResult(playerName, frameResults.getValue());

            frameSet = frameSet.readyNext();
        }
    }
}
