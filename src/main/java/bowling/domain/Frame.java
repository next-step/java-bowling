package bowling.domain;

public class Frame {


    private Score score;
    private Frame nextFrame;

    private Type type = Type.NONE;

    private Frame() {
    }

    private Frame(Frame frame) {
        this.nextFrame = frame;
    }

    public static Frame of() {
        return new Frame();
    }

    public static Frame ofNextFrame(Frame frame) {
        return new Frame(frame);
    }

    public Type first(int point) {
        if (point > 10 || point < 0) {
            throw new IllegalArgumentException("");
        }

        this.first = point;
        if (point == 10) {
            this.type = Type.STRIKE;
            return Type.STRIKE;
        }

        return Type.NONE;
    }

    public Type second(int point) {
        if (this.first + point > 10) {
            throw new IllegalArgumentException("");
        }
        if (this.first + point == 10) {
            this.type = Type.SPARE;
        }
        if (this.first + point > 0 && this.first + point < 10) {
            this.type = Type.MISS;
        }
        if (this.first + point == 0) {
            this.type = Type.MISS;
        }
        this.type = Type.GUTTER;

        return this.type;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public Type getType() {
        return type;
    }
}
