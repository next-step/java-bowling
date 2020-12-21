package bowling;

import bowling.view.GameStatus;

import java.util.List;
import java.util.function.Consumer;

import static java.util.stream.Collectors.toList;

public class Game {

    private List<Frames> framesList;

    public Game(List<String> playerNames) {
        framesList = playerNames
                .stream()
                .map(Frames::new)
                .collect(toList());
    }

    public List<GameStatus> start() {
        return framesList.stream()
                .map(Frames::start)
                .collect(toList());
    }

    public boolean isFinish() {
        return false;
    }

    public void forEach(Consumer<Frames> frameConsumer) {
        framesList.forEach(frameConsumer);
    }
}
