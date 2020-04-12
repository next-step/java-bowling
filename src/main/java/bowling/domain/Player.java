package bowling.domain;

public class Player {
    private String name = "";
    private Frames frames;

    public String getName() {
        return name;
    }

    public Frames getFrames() {
        return frames;
    }

    public Player(String name) {
        checkLetter(name);
        this.name = name;
        this.frames = new Frames();
    }

    @Override
    public String toString() {
        return name.toString();
    }

    public void checkLetter(String name) {
        if (name.length() > 3) {
            throw new IllegalArgumentException("3글자까지만 입력가능합니다.");
        }
    }

    public int currentFrame() {
        return frames.currentFrame() + 1;
    }

    public void addFrame(NormalFrame normalFrame) {
        frames.addNormalFrame(normalFrame);
    }

    public boolean isNextFrame() {
        return frames.isNextFrame();
    }

    public boolean isEndNormalFrame() {
        return frames.isEndNormalFrame();
    }


    public boolean isEndFinalFrame(FinalFrame finalFrame) {
        addFrame(finalFrame);
        return frames.isEndFinalFrame();
    }

    public void addFrame(FinalFrame finalFrame) {
        frames.addFinalFrame(finalFrame);
    }
}
