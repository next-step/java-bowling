package bowling.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created : 2020-12-16 오전 9:30
 * Developer : Seo
 */
public class Frame {
    private static final Random random = new Random();
    public static final String NORMAL_FRAME_NO_PREFIX = "0";
    public static final int INIT = 0;
    public static final int NEXT = 1;
    public static final int FINAL = 10;
    public static final int PINS = 10;

    protected final int frameNo;
    protected final Score score;

    public Frame(int frameNo, Score score) {
        this.frameNo = frameNo;
        this.score = score;
        if (frameNo < 11) {
            shot();
            System.out.println(frameNo + " : " + score.get());
        }
    }

    private Frame shot() {
        if (isStrike()) {
            return nextFrame(bowl());
        }
        secondShot(bowl());
        return nextFrame(bowl());
    }

    public Frame(Score score) {
        this.frameNo = FINAL;
        this.score = score;
    }

    public Frame() {
        this.frameNo = INIT;
        this.score = new Score(INIT);
        nextFrame(score);
    }

    public static List<Frame> init() {
        List<Frame> frames = new LinkedList<>();
        frames.add(new Frame());
        return frames;
    }

    public boolean isStrike() {
        return score.get() == Symbol.STRIKE.getScore();
    }

    public boolean isSpare(Score second) {
        return score.get() != Symbol.STRIKE.getScore()
                && score.get() + second.get() == Symbol.SPARE.getScore();
    }

    public Frame nextFrame(Score score) {
        //return frameNo + NEXT < FINAL ? new NormalFrame(frameNo + NEXT, score) : new FinalFrame(score);
        return new Frame(frameNo + NEXT, score);
    }

    public Score bowl() {
        return new Score(random.nextInt(PINS + NEXT));
    }

    public void secondShot(Score second) {
        this.score.set(second.get());
    }
}
