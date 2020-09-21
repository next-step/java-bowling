package bowling.app;

import bowling.domain.Player;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BowlingExecutor {

    private BowlingExecutor() {

    }

    public static void execute(int playerCount) {
        List<Player> players = IntStream.range(0, playerCount)
                .mapToObj(i -> new Player(InputView.getPlayerName()))
                .collect(Collectors.toList());
        OutputView.printBoard(players);
        List<Frame> frames = new ArrayList<>();
        while (!isAllEnd(frames)) {
            frames = players.stream()
                    .map(player -> {
                        Frame frame = player.getFrame();
                        Frame nextFrame = frame;
                        while (!frame.isCompleted()) {
                            int numberOfPins = InputView.getNumberOfPins(player);
                            nextFrame = player.bowl(numberOfPins);
                            OutputView.printBoard(players);
                        }
                        return nextFrame;
                    })
                    .collect(Collectors.toList());
        }
    }

    private static boolean isAllEnd(List<Frame> frames) {
        return frames.isEmpty() ?
                false :
                frames.stream()
                        .allMatch(Frame::isEnd);
    }

}
