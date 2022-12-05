package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.exception.DoNotHaveEnoughPointsException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {
    private static final int FIRST_NUMBER = 1;
    private final List<Frame> frames = new ArrayList<>();
    private int current;

    public Frames() {
        this.current = FIRST_NUMBER;
        frames.add(new NormalFrame(this.current));
    }

    public boolean bowl(Point point) {
        Frame frame = frames.get(current - 1);
        Frame nextFrame = frame.bowl(point);
        if (nextFrame == null) {
            return false;
        }
        if (frame != nextFrame) {
            current += 1;
            frames.add(nextFrame);
        }
        return true;
    }

    public List<List<Point>> getPointsOfFrames() {
        List<List<Point>> pointsOfFrames = new ArrayList<>();
        for (Frame frame : frames) {
            try {
                pointsOfFrames.add(frame.getPoints());
            } catch (DoNotHaveEnoughPointsException e) {
                break;
            }
        }
        return Collections.unmodifiableList(pointsOfFrames);
    }

    public List<Point> getTotalPointsOfFrames() {
        List<Point> totalPointsOfFrames = new ArrayList<>();
        for (Frame frame : frames) {
            try {
                totalPointsOfFrames.add(frame.calculateTotalPoint());
            } catch (DoNotHaveEnoughPointsException e) {
                break;
            }
        }
        for (int i = 1; i < totalPointsOfFrames.size(); i++) {
            totalPointsOfFrames.set(i, totalPointsOfFrames.get(i).add(totalPointsOfFrames.get(i - 1)));
        }
        return Collections.unmodifiableList(totalPointsOfFrames);
    }

    public boolean isFinished() {
        return frames.get(frames.size() - 1)
                .isFinished();
    }

}
