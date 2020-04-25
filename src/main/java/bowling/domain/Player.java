package bowling.domain;

public class Player {
    private final String name;
    private final Frames frames;

    private Player(String name, Frames frames) {
        this.name = name;
        this.frames = frames;
    }

    public static Player of(String name) {
        if (name.length() > 3) {
            throw new IllegalArgumentException(String.format("create Player fail. name length must be under 4 characters, name = %s", name));
        }

        return new Player(name, new Frames());
    }

    public int getCurrentFrameNumber() {
        return frames.getCurrentFrameNumber();
    }

    int getCurrentFrameShotCount(){
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
}
