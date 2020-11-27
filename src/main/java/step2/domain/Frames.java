package step2.domain;

import java.util.*;

import static java.util.Collections.singletonList;

public class Frames {
    public static final String ERROR_NOT_EXISTS_FRAME = "존재하지 않는 프레임입니다.";
    public static final String NO_MARK = "";

    private final int size;
    private final Frame head;
    private final Frame tail;


    public static Builder Builder() {
        return new Builder();
    }

    public Frame getLastCompletedFrame(Frame frame) {
        if (!frame.isFinished() || (frame.isFinished() && frame instanceof FinalFrame)) {
            return frame;
        }
        return getLastCompletedFrame(frame.next());
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

    public Frame getTail() {
        return tail;
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

    public List<String> getScores(Frame frame) {
        Frame current = frame;
        List<String> list = new ArrayList<>(singletonList(String.valueOf(current.getScore())));
        int cumulativePoint = 0;

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

    public static class Builder {

        private int size;
        private Frame head;
        private Frame tail;

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

        public Builder tail(Frame tail) {
            this.tail = tail;
            return this;
        }

        public Frames build() {
            return new Frames(this);
        }
    }

    public Frames(Builder builder) {
        this.size = builder.size;
        this.head = builder.head;
        this.tail = getLast();
    }
}
