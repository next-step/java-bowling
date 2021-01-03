package bowling.domain;

import bowling.domain.frame.Frame;
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

    public Bowling(Players players) {
        this.players = players;
    }

    public void games() {
        ResultView.init();
        IntStream.rangeClosed(INDEX_ZERO, FINAL_FRAME).forEach(this::game);
    }

    private void game(int frameNo) {
        players.getPlayers().forEach(player -> {
            Frame frame = player.stroke(frameNo, InputView.getPins(player, frameNo));
            ResultView.print(players);

            State state = frame.getState();
            while (!state.isFinished()) {
                player.spare(frameNo, InputView.getPins(player, frameNo));
                state = frame.getState();
                ResultView.print(players);
            }
        });
    }
}
