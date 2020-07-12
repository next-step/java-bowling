package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private static final int COUNT_OF_FRAMES = 10;
    private static final int FRAME_INDEX_INITIAL = 0;
    private static final int FRAME_DIFFERENCE_INDEX_COUNT = 1;
    private static final int LAST_CALCULATED_FRAME_INDEX_INITIAL = 0;

    private final List<Frame> frames;
    private int frameIndex;
    private int lastCalculatedFrameIndex;

    private Frames() {
        frames = new ArrayList<>();

        for (int i = 1; i < COUNT_OF_FRAMES; i++) {
            frames.add(new NormalFrame());
        }

        frames.add(new FinalFrame());

        this.frameIndex = FRAME_INDEX_INITIAL;
        this.lastCalculatedFrameIndex = LAST_CALCULATED_FRAME_INDEX_INITIAL;
    }

    public static Frames init() {
        return new Frames();
    }

    public int size() {
        return frames.size();
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public boolean canPlay() {
        if (frameIndex == FRAME_INDEX_INITIAL) {
            return true;
        }

        if (frameIndex >= COUNT_OF_FRAMES) {
            return false;
        }

        return frames.get(frameIndex).isRollable();
    }

    public void play(int knockedDownPinCount) {
        Frame currentFrame = frames.get(frameIndex);
        currentFrame.rollingBall(knockedDownPinCount);
        calculateScore(knockedDownPinCount);

        if (!currentFrame.isRollable()) {
            ++frameIndex;
        }
    }

    public int getFrameNumber() {
        return frameIndex + FRAME_DIFFERENCE_INDEX_COUNT;
    }

    /*
    이전 프레임의 점수 계산이 안끝난 경우 (lastCalculatedFrameIndex < frameIndex)
        * 현재 프레임의 rolling 값을 더해줌
        * scoreAddableCount 는 무조건 빼주기

    현재 프레임의 점수를 계산해야 할 때 (lastCalculatedFrameIndex == frameIndex)
        * 현재 프레임의 점수 계산
            * spare/strike
                * scoreAddableCount 세팅
                * lastCalculatedFrameIndex 그대로
            * 이외
                * scoreAddableCount 세팅 X
                * lastCalculatedFrameIndex 그대

   * */
    private void calculateScore(int knockedDownPinCount) {
        Frame lastCalculatedFrame = frames.get(lastCalculatedFrameIndex);

        if (lastCalculatedFrame.isRollable()) {
            return;
        }

        // 첫번쨰 프레임인 경우
        if (frameIndex == LAST_CALCULATED_FRAME_INDEX_INITIAL) {
            lastCalculatedFrame.calculateInitialScore();

            if (lastCalculatedFrame.isScoreCalculateDone()) {
                lastCalculatedFrameIndex++;
            }

            return;
        }

        // 현재 게임중인 프레임의 점수 계산
        if (lastCalculatedFrameIndex == frameIndex) {
            lastCalculatedFrame.calculateScore(frames.get(lastCalculatedFrameIndex - 1));

            if (lastCalculatedFrame.isScoreCalculateDone()) {
                lastCalculatedFrameIndex++;
            }

            return;
        }

        // 계산안된 프레임이 존재하는 경우
        lastCalculatedFrame.addScore(knockedDownPinCount);
        if (!lastCalculatedFrame.isScoreCalculateDone()) {
            return;
        }

        ++lastCalculatedFrameIndex;
        for (int index = lastCalculatedFrameIndex; index <= frameIndex; index++) {
            lastCalculatedFrame = frames.get(index);
            if (lastCalculatedFrame.isRollable()) {
                break;
            }

            lastCalculatedFrame.calculateScore(frames.get(index - 1));
            if (lastCalculatedFrame.isScoreCalculateDone()) {
                ++lastCalculatedFrameIndex;
                continue;
            }

            for (int j = index + 1; j <= frameIndex; j++) {
                Frame nextFrame = frames.get(j);

                nextFrame.addAdditionalScoreOfLastFrame(lastCalculatedFrame);
                if (lastCalculatedFrame.isScoreCalculateDone()) {
                    break;
                }
            }
        }
    }
}
