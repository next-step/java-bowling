package bowling.entity;

import java.util.Objects;
import java.util.Optional;

public class BowlingGame {

    private final User user;
    private final Frames frames;

    public BowlingGame(User user) {
        this.user = user;
        frames = Frames.initialize();
    }

    /**
     * 1 프레임부터 해당 프레임까지의 점수 합
     * 아직 계산이 완료되지 안핬다면 empty 반환
     */
    public Optional<Score> getTotalScoreFromFirst(int toFrameNumber) {

        Score totalScore = Score.zero();

        for (int frameNumber = 1; frameNumber <= toFrameNumber; frameNumber++) {

        }

        return Optional.empty();
    }

    private Optional<Score> getTotalScore(int frameNumber) {

        Pitch nextFirstPitch = frames.getFrame(frameNumber + 1)
                .map(Frame::getPitch)
                .orElse(null);

        Pitch nextSecondPitch = frames.getFrame(frameNumber + 2)
                .map(Frame::getPitch)
                .orElse(null);

        BonusScore bonusScore = new BonusScore(nextFirstPitch, nextSecondPitch);

        return frames.getFrame(frameNumber)
                .map(frame -> frame.getTotalScore(bonusScore))
                .filter(Optional::isPresent)
                .map(Optional::get);
    }

    public void bowl(Score score) {
        frames.getCurrentFrame()
                .bowl(score);
        addFrameIfNeedMoreFrame();
    }

    public void addFrameIfNeedMoreFrame() {

        FrameResult tenthFrameResult = frames.getFrame(10)
                .map(Frame::getFrameResult)
                .orElse(null);
        FrameResult eleventhFrameResult = frames.getFrame(11)
                .map(Frame::getFrameResult)
                .orElse(null);

        if (Objects.isNull(eleventhFrameResult) &&
                tenthFrameResult == FrameResult.STRIKE || tenthFrameResult == FrameResult.SPARE) {
            frames.moreFrame();
        }

        if (tenthFrameResult == FrameResult.STRIKE && eleventhFrameResult == FrameResult.STRIKE) {
            frames.moreFrame();
        }
    }

    public int getCurrentFrameNumber() {
        return frames.getCurrentFrameNumber();
    }

    public boolean hasNotFinishedFrame() {
        return frames.hasNotFinishedFrame();
    }

    public User getUser() {
        return user;
    }

    public Frames getFrames() {
        return frames;
    }

}
