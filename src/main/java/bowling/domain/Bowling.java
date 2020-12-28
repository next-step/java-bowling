package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.state.State;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.stream.IntStream;

/**
 * Created : 2020-12-17 오후 12:40
 * Developer : Seo
 */
public class Bowling {
    public static final int FINAL_FRAME = 9;
    public static final int INDEX_ZERO = 0;
    public static final int INDEX_ONE = 1;

    private final Players players;
    private final Frames frames;

    public Bowling(Players players, Frames frames) {
        this.players = players;
        this.frames = frames;
    }

    public void game() {
        ResultView.init(frames, players);
        IntStream.rangeClosed(INDEX_ZERO, FINAL_FRAME)
                .forEach(frameNo -> {
                    bowl(frameNo);
                    frames.next(frameNo);
                });
    }

    private void bowl(int frameNo) {
        for (int playerIndex = INDEX_ZERO; playerIndex < players.size(); playerIndex++) {
            stroke(frameNo, playerIndex);
        }
    }

    private void stroke(int frameNo, int playerIndex) {
        Frame frame = frames.get(frameNo);
        State state = frame.stroke(playerIndex, InputView.getPins(players.get(playerIndex)));
        ResultView.print(frames, players);
        while (!state.isFinished()) {
            state = frame.spare(playerIndex, InputView.getPins(players.get(playerIndex)));
            ResultView.print(frames, players);
        }
    }
}
