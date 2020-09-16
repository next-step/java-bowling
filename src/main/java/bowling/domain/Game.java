package bowling.domain;

import java.util.*;
import java.util.stream.Collectors;

public class Game {
    private LinkedList<Frame> frames;
    private Map<Integer, List<String>> results;

    private Game() {
//        frames = new LinkedList<Frame>() {{
//            add(NormalFrame.from());
//        }};
//        results = new HashMap<>();
    }

    public static Game start() {
        return new Game();
    }

    public boolean isFinish() {
//        Frame lastFrame = getLastFrame();
//        return !lastFrame.hasNextFrame() && lastFrame.isFinish();
        return false;
    }

    public int getPlayNumber() {
        return getLastFrame().getNumber();
    }

    public List<List<String>> hit(int count) {
//        Frame frame = getLastFrame().hit(count);
//
//        results.put(frame.getNumber(), frame.value());
//
//        addNextFrame();
//
//        return toHitResult();
        return null;
    }

    public List<Integer> getSumScores() {
        List<Integer> result = new ArrayList<>();

        List<Frame> frameSet = frames.stream()
                .filter(Frame::isFinish)
                .collect(Collectors.toList());

        setScore(result, frameSet);

        return result;
    }

    private void setScore(List<Integer> result, List<Frame> frames) {
//        for (ListIterator<Frame> node = frames.listIterator(); node.hasNext(); ) {
//            Frame frame = node.next();
//            int nextIndex = node.nextIndex();
//
//            Score score = frame.getScore();
//
//            int sum = sumScore(score, nextIndex, frames);
//            sum = addMore(result, sum);
//
//            result.add(sum);
//        }

    }

    private int addMore(List<Integer> result, int score) {
        if (!result.isEmpty()) {
            score += result.get(result.size() - 1);
        }

        return score;
    }

    private int sumScore(Score score, int nextIndex, List<Frame> frames) {
        while (score.canNextSum() && nextIndex < frames.size()) {
            score = sumMore(frames.get(nextIndex), score);
            nextIndex = nextIndex + 1;
        }

        return score.toInt();
    }

    private Score sumMore(Frame nextFrame, Score score) {
        if (nextFrame.isFinish()) {
            score = nextFrame.additionalScore(score);
        }

        return score;
    }

    private void addNextFrame() {
//        Frame lastFrame = getLastFrame();
//
//        if (lastFrame.isFinish() && lastFrame.hasNextFrame()) {
//            frames.add(lastFrame.next());
//        }
    }

    private Frame next() {
//        if (!hasNextFrame()) {
//            throw new RuntimeException("다음 프레임이 존재하지 않습니다.");
//        }
//
//        int nextFrameNumber = nextFrameNumber();
//        return nextFrameNumber == LAST_FRAME_NUMBER ? LastFrame.from() : NormalFrame.of(nextFrameNumber);
        return null;
    }

    public boolean hasNextFrame() {
//        return nextFrameNumber() <= LAST_FRAME_NUMBER;
        return false;
    }

    private int nextFrameNumber() {
//        return number + 1;
        return 0;
    }

    private List<List<String>> toHitResult() {
        return new ArrayList<>(results.values());
    }

    private Frame getLastFrame() {
        return frames.get(frames.size() - 1);
    }
}
