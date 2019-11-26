package bowling.domain;

import java.util.List;

public class Table {

    private User user;
    private Frames frames;

    public Table(User user) {
        this.user = user;
        this.frames = new Frames();
    }

    public User getUser() {
        return this.user;
    }

    public List<Frame> getFrames() {
        return this.frames.getFrames();
    }

    public boolean hasNext() {
        return frames.hasNext();
    }

    public int getNext() {
        return frames.getThisTurn();
    }

    public void bowl(int pin) {
        frames.bowl(pin);
    }
}
