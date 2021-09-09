package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import java.util.HashMap;
import java.util.Map;

public class ScoreBoard {

    private final Map<Person, Frames> board;

    private ScoreBoard(Map<Person, Frames> board) {
        this.board = board;
    }

    public static ScoreBoard create() {
        return new ScoreBoard(new HashMap<>());
    }

    public void write(Person person, Frame frame) {
        Frames frames = board.computeIfAbsent(person, person1 -> Frames.create());
        frames.add(frame);
    }

    public Frames framesOfPerson(Person person) {
        return board.get(person);
    }
}
