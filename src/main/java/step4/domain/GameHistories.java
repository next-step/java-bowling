package step4.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static java.util.Collections.unmodifiableList;

public class GameHistories {
    private final List<GameHistory> historyList;

    public GameHistories(List<GameHistory> historyList) {
        this.historyList = unmodifiableList(historyList);
    }

    public static GameHistories of(GameHistory... histories) {
        return new GameHistories(new ArrayList<>(Arrays.asList(histories)));
    }

    public void forEach(Consumer<GameHistory> consumer) {
        historyList.forEach(consumer);
    }

}
