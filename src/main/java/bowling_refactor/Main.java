package bowling_refactor;

import bowling_refactor.domain.FrameNumber;
import bowling_refactor.domain.Player;
import bowling_refactor.domain.Players;
import bowling_refactor.view.InputView;
import bowling_refactor.view.ResultView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        int bowlerCount = InputView.inputPlayerCount();
        List<Player> bowlerList = new ArrayList<>();
        for (int i = 0; i < bowlerCount; i++) {
            bowlerList.add(new Player(InputView.inputUserNames()));
        }

        Players players = new Players(bowlerList);
        ResultView.printScoreBoardPlayers(players);
        IntStream.range(0, FrameNumber.MAX_FRAME_NUMBER)
                .forEach(frameNumber -> eachPlayerPlay(players, frameNumber));

    }

    private static void eachPlayerPlay(Players players, int frame) {
        for (int i = 0; i < players.size(); i++) {
            play(players, players.getPlayer(i), frame);
        }
    }

    private static void play(Players players, Player bowler, int frameNumber) {
        while (bowler.isLeftPin(frameNumber)) {
            int pin = InputView.inputPinCount(bowler.getPlayer());
            bowler.hit(frameNumber, pin);
            ResultView.printScoreBoardPlayers(players);
        }
    }
}
