package domain;


public class BowlingGame {
    private static Frames frames;
    private static Frame frame;

    public static void startGame() {
        frames = new Frames();
        frame = new NormalFrame(1);
        addFrame();
    }

    public static void throwBall(int inputThrowBall) {
        frame.addPin(Pin.of(inputThrowBall));
    }

    public static void addFrame() {
        frames.add(frame);
    }

    public static void next() {
        frame = frame.nextFrame();
    }

    public static Frames getFrames() {
        return frames;
    }

    public static boolean isStrike() {
        return frame.isStrikePass();
    }

    public static boolean isEmptyChane() {
        return frame.isEmptyChane();
    }

    public static void play(int inputThrowBall) {
        throwBall(inputThrowBall);
        if (isStrike() || isEmptyChane()) {
            next();
            addFrame();
        }
    }

    public static boolean hasNext() {
        return frames.getSize() <= Frame.MAX_FRAME;
    }
}
