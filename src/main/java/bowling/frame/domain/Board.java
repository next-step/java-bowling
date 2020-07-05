package bowling.frame.domain;

import bowling.pin.domain.Pin;
import bowling.user.domain.User;
import java.util.Comparator;

public class Board {

    private final User user;
    private final Frame first;
    private Frame current;

    public Board(User user) {
        this.user = user;
        this.first = Frame.init();
        this.current = first;
    }

    public static Board init(User user) {
        return new Board(user);
    }

    public void roll(Pin felled) {
        current = current.roll(felled);
    }

    public User getUser() {
        return user;
    }

    public Frame getFirst() {
        return first;
    }

    public Index getLastIndex() {
        return current.getIndex();
    }

    public boolean isGameOver() {
        return current.isEnd();
    }

}
