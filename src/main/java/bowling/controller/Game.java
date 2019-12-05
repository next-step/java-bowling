package bowling.controller;

import bowling.View;
import bowling.domain.Histories;
import bowling.domain.Player;
import bowling.domain.set.FrameSet;
import bowling.domain.set.NormalFrameSet;

public class Game {

    private Game() {
    }

    public static void play(View view) {
        Player player = Player.create(view.getName());

        FrameSet frameSet = NormalFrameSet.createFirst();
        Histories histories = new Histories();

        while (true) {
            int hitCount = view.getHitCount(frameSet.getPlayCount());
            frameSet.play(hitCount);

            histories.add(frameSet);

            view.showFrameSetResult(player.getName(), histories.getValue(), histories.getScores());

            if (frameSet.isEndedGame()) {
                break;
            }

            if (frameSet.isEndedFrame()) {
                frameSet = frameSet.getNext();
            }
        }
    }
}
