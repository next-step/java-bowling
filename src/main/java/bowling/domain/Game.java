package bowling.domain;

import java.util.*;
import java.util.stream.Collectors;

public class Game {
    private LinkedList<Frame> frames;
    private Map<Integer, List<String>> results;

    private Game() {
        frames = new LinkedList<Frame>() {{
            add(NormalFrame.from());
        }};
        results = new HashMap<>();
    }

    public static Game start() {
        return new Game();
    }

    public boolean isFinish() {
        Frame lastFrame = getLastFrame();
        return !lastFrame.hasNextFrame() && lastFrame.isFinish();
    }

    public int getPlayNumber() {
        return getLastFrame().getNumber();
    }

    public List<List<String>> hit(int count) {
        Frame frame = getLastFrame().hit(count);

        results.put(frame.getNumber(), frame.value());

        addNextFrame();

        return toHitResult();
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
        for (ListIterator<Frame> node = frames.listIterator(); node.hasNext(); ) {
            Frame frame = node.next();
            int nextIndex = node.nextIndex();

            Score score = frame.getScore();
            int sum = sumScore(score, nextIndex, frames);

            if (!result.isEmpty()) {
                sum += result.get(result.size() - 1);
            }

            result.add(sum);
        }
    }

    private int sumScore(Score score, int nextIndex, List<Frame> frames) {
        while (score.canNextSum() && nextIndex < frames.size()) {
            Frame nextFrame = frames.get(nextIndex);

            if (nextFrame.isFinish()) {
                score = nextFrame.sumScore(score);
            }

            nextIndex = nextIndex + 1;
        }

        return score.toInt();
    }

    private void addNextFrame() {
        Frame lastFrame = getLastFrame();

        if (lastFrame.isFinish() && lastFrame.hasNextFrame()) {
            frames.add(lastFrame.next());
        }
    }

    private List<List<String>> toHitResult() {
        return results.entrySet()
                .stream()
                .map(entry -> entry.getValue())
                .collect(Collectors.toList());
    }

    private Frame getLastFrame() {
        return frames.get(frames.size() - 1);
    }
}
