package bowling;

import bowling.domain.factory.HitScoresFactory;
import bowling.domain.scores.GeneralHitScores;
import bowling.domain.scores.HitScores;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Frames {

    private static final int START_FRAME_NUMBER = 0;
    private static final int ONE_NUMBER = 1;

    private final List<Frame> frames;

    public Frames() {
        this(new ArrayList<>());
    }

    public Frames(HitScoresFactory hitScoresFactory, int... numbers) {
        this(toFrame(hitScoresFactory, numbers));
    }


    public Frames(Frame... frames) {
        this(Arrays.asList(frames));
    }


    public Frames(List<Frame> frames) {
        if (frames.isEmpty()) {
            frames.add(new Frame(new GeneralHitScores()));
        }

        this.frames = frames;
    }


    public int round() {
        return frames.size();
    }

    public boolean isFrameClosed() {
        return getLastFrame().isClosedStroke();
    }

    // 마지막 프레임을 가져온다. ifEmpty() 이면, 프레임을 생성한다.
    public Frames add(HitScores hitScores) {

        Frame lastFrame = getLastFrame();

        if (!lastFrame.isClosedStroke()) { // lastFrameStrokeIsClosed() 마지막 프레임이 닫혀있으면, 신규 프레임에 추가.
            throw new IllegalArgumentException("프레임의 투구가 끝나지 않았습니다.");
        }

        frames.add(new Frame(hitScores));
        return new Frames(frames);
    }

    public Frames update(int hitCount) {
        Frame lastFrame = getLastFrame();

        Frame updatedFrame = lastFrame.updateScore(hitCount);
        frames.set(frames.size() - ONE_NUMBER, updatedFrame);

        return new Frames(frames);
    }


    @Deprecated
    public Frames add(int hitCount) {
        Frame updatedFrame = getLastFrame().updateScore(hitCount);
        frames.set(frames.size() - ONE_NUMBER, updatedFrame);

        return new Frames(frames);
    }


    public int size() {
        return frames.size();
    }

    public Frame getLastFrame() {
        return frames.get(frames.size() - ONE_NUMBER);
    }

    private static List<Frame> toFrame(HitScoresFactory hitScoresFactory, int[] numbers) {
        return IntStream.range(0, numbers.length)
            .boxed()
            .map(round -> new Frame(hitScoresFactory.create(round, numbers[round])))
            .collect(Collectors.toList());
    }
}
