package bowling.domain.referee;

import bowling.domain.Player;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.frame.NormalFrame;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Referee {

    private final Map<Player, Frames> board;

    private Referee(Map<Player, Frames> scores) {
        this.board = scores;
    }

    public static Referee create() {
        return new Referee(new HashMap<>());
    }

    public void write(Player person, Frame frame) {
        Frames frames = board.get(person);
        frames.addFrame(frame);
    }

    public Frames framesOfPerson(Player player) {
        readyPerson(player);
        return board.get(player);
    }

    public void readyPerson(Player givenPlayer) {
        board.computeIfAbsent(givenPlayer, person -> Frames.from(NormalFrame.create()));
    }

    public Set<Player> persons() {
        return board.keySet();
    }

    public boolean isEndFrame(int currentFrame) {
        Set<Player> persons = persons();

        return persons.stream()
            .map(board::get)
            .map(Frames::latestFrame)
            .allMatch(frame -> frame.isFrameEnd(currentFrame));
    }
}
