package bowling.domain;

public class Player {
    private final String name;
    private final Frames frames;

    Player(String name, Frames frames) {
        this.name = name;
        this.frames = frames;
    }

    public static Player ofName(String name) {
        if (name.length() > 3) {
            throw new IllegalArgumentException(String.format("create Player fail. name length must be under 4 characters, name = %s", name));
        }

        return new Player(name, Frames.ofDefaultInit());
    }

    public int getCurrentFrameNumber() {
        return frames.getCurrentFrameNumber();
    }

    int getCurrentFrameShotCount() {
        return frames.getCurrentFrameShotCount();
    }

    public void shot(int shot) {
        frames.shot(shot);
    }

    public boolean isGameSet() {
        return frames.isGameSet();
    }

    public String name() {
        return name;
    }

    public Frames frames() {
        return frames;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", frames=" + frames +
                '}';
    }
}
