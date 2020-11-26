package step2.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class GameHistories {
    private List<GameHistory> historyList = new ArrayList<>();

    public GameHistories() { }

    public void addHistory(GameHistory history) {
        historyList.add(history);
    }

    public void lock() {
        historyList = Collections.unmodifiableList(historyList);
    }

    public void forEach(Consumer<GameHistory> consumer) {
        historyList.forEach(consumer);
    }
}
