package bowling.domain;

import java.util.HashMap;
import java.util.Map;

public class BowlingBoard {

    private final Map<Person, Frames> board;
    /*private final List<Frame> frames;*/


    public BowlingBoard(Map<Person, Frames> board) {
        this.board = board;
    }

    /*private BowlingBoard(List<Frame> frames) {
        this.frames = frames;
    }*/

    public static BowlingBoard create() {
        return new BowlingBoard(new HashMap<>());
    }

    public void write(Person person, Frame frame) {
        Frames frames = board.computeIfAbsent(person, person1 -> new Frames());
        frames.add(frame);
    }

    public Frames get(Person person) {
        return board.get(person);
    }
}
