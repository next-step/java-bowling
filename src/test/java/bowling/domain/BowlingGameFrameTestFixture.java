package bowling.domain;

public class BowlingGameFrameTestFixture {

    private BowlingGameFrameTestFixture() {
    }

    public static BowlingGameFrame createNormalBowlingGameFrame(int hit) {
        BowlingGameFrame frame = new NormalBowlingGameFrame();
        frame.add(hit);
        return frame;
    }

    public static BowlingGameFrame createNormalBowlingGameFrame(int hit1, int hit2) {
        BowlingGameFrame frame = new NormalBowlingGameFrame();
        frame.add(hit1);
        frame.add(hit2);
        return frame;
    }

    public static BowlingGameFrame createFinalBowlingGameFrame(int hit) {
        BowlingGameFrame frame = new FinalBowlingGameFrame();
        frame.add(hit);
        return frame;
    }

    public static BowlingGameFrame createFinalBowlingGameFrame(int hit1, int hit2) {
        BowlingGameFrame frame = new FinalBowlingGameFrame();
        frame.add(hit1);
        frame.add(hit2);
        return frame;
    }

    public static BowlingGameFrame createFinalBowlingGameFrame(int hit1, int hit2, int hit3) {
        BowlingGameFrame frame = new FinalBowlingGameFrame();
        frame.add(hit1);
        frame.add(hit2);
        frame.add(hit3);
        return frame;
    }

}
