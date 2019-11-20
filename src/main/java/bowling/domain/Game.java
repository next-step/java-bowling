package bowling.domain;

import bowling.View;
import bowling.domain.state.State;

public class Game {

    private Game() { }

    public static void play(View view) {
        FrameSet frameSet = FrameSet.createFirst();

        while (!frameSet.isEnd()) {
            int hitCountText = view.getHitCount(frameSet.getPlayCount());
            State currentState = frameSet.play(hitCountText);
            view.showFrameSetResult(currentState);

            frameSet = frameSet.readyNextSet();
        }
    }
}
