package bowling.domain;

public interface BowlingFrame {

    BowlingFrame secondPitching(Point point);

    BowlingFrame firstPitching(Point point);

    BowlingFrame nextFrame();

    boolean isEnd();

    boolean isStrike();

}
