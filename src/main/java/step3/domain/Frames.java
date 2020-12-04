package step3.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static java.util.Collections.singletonList;

public class Frames {
    public static final String ERROR_NOT_EXISTS_FRAME = "존재하지 않는 프레임입니다.";
    public static final String NO_MARK = "";

    private final int size;
    private final Frame head;
    private Frame cursor;

    public static Builder Builder() {
        return new Builder();
    }

    public Frame getLastCompletedFrame(Frame frame) {
        if (!frame.isFinished()) {
            return frame;
        }
        return getLastCompletedFrame(frame.next());
    }

    public Frame getCursor() {
        return cursor;
    }

    public Frame getLast() {
        return getFrame(size - 1);
    }

    public Frame getFrame(int frameNo) {
        return findByFrameNo(frameNo, head);
    }

    private Frame findByFrameNo(int frameNo, Frame current) {
        if (current.getFrameNo() == frameNo) {
            return current;
        }
        isValidNextFrame(current);
        return findByFrameNo(frameNo, current.next());
    }

    private void isValidNextFrame(Frame current) {
        if (!current.hasNext()) {
            throw new NoSuchElementException(ERROR_NOT_EXISTS_FRAME);
        }
    }

    public int size() {
        return size;
    }

    public Frame getHead() {
        return head;
    }

    public List<String> getMarks(Frame frame) {
        Frame current = frame;
        List<String> list = new ArrayList<>(singletonList(current.getResultString()));

        while (current.hasNext()) {
            current = current.next();
            list.add(current.getResultString());
        }
        return list;
    }

    public int getScores() {
        Frame current = head;
        int totalPoint = head.getScore();

        while (current.hasNext()) {
            current = current.next();
            totalPoint += current.getScore();
        }

        return totalPoint;
    }

    public List<String> getScores(Frame frame) {
        Frame current = frame;
        List<String> list = new ArrayList<>(singletonList(String.valueOf(current.getScore())));
        int cumulativePoint = current.getScore();

        while (current.hasNext()) {
            current = current.next();
            cumulativePoint += current.getScore();

            list.add(formattedScore(cumulativePoint, current));
        }
        return list;
    }

    private String formattedScore(int value, Frame frame) {
        if (frame.getScore() == 0 && !frame.isFinished()) {
            return NO_MARK;
        }
        return String.valueOf(value);
    }

    public boolean isFinished() {
        return getLast().isFinished();
    }

    public Frame getCurrentFrame() {
        return getLastCompletedFrame(head);
    }

    public void updateCursor() {
        cursor = getLastCompletedFrame(cursor);
    }


    public static class Builder {

        private int size;
        private Frame head;

        public Builder() {
        }

        public Builder size(int size) {
            this.size = size;
            return this;
        }

        public Builder head(Frame head) {
            this.head = head;
            return this;
        }

        public Frames build() {
            return new Frames(this);
        }
    }

    public Frames(Builder builder) {
        this.size = builder.size;
        this.head = builder.head;
        this.cursor = builder.head;
    }
}
