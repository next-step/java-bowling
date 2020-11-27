package bowling.domain;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class Game {
    private final Map<Player, Board> boardMap = new HashMap<>();
    private final List<RollSubject> subjects = new LinkedList<>();

    void addPlayer(Player player, Supplier<Roll> supplier) {
        Board board = new Board();
        RollSubject subject = new RollSubject(supplier);
        subject.register(new BoardObserver(board));
        boardMap.put(player, board);
        subjects.add(subject);
    }

    void play() {
        subjects.stream()
                .forEach(Subject::execute);
    }
}
