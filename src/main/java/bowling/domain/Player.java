package bowling.domain;

public class Player {
    private String name;
    private Frames frames;

    public Player(String name) {
        this.name = name;
        this.frames = new Frames();
    }

    public String getName() {
        return name;
    }

    public Frame frameByIndex(int index) {
        return frames.frameByIndex(index);
    }

    public boolean isFallDownAble(int i) {
        return frames.isFallDownAble(i);
    }

    public void fallDown(int index, int pinCount) {
        frames.fallDown(index, pinCount);
    }

    public int getScore(int index) {
        return frames.getScore(index).getScore();
    }
}
