package step2.domain;

import step2.domain.dto.PlayerDTO;
import step2.strategy.BowlingPitchesStrategy;

public class BowlingGame {

    public static final int FRAME_FIRST_NO = 0;
    public static final int FRAME_LAST_NO = 8;
    public static final int FRAME_SIZE = 10;
    public static final int PLUS_NUMBER = 1;

    public static Frames build() {
        return Frames.Builder()
                .size(FRAME_SIZE)
                .head(NormalFrame.of(FRAME_FIRST_NO))
                .build();
    }

    public static GameHistories start(PlayerDTO playerDTO, BowlingPitchesStrategy strategy) {
        boolean loop = true;
        GameHistories gameHistories = new GameHistories();
        Frames frames = playerDTO.getFrames();
        Frame frame = frames.getHead();

        while (loop) {
            int pitches = frame.pitches(strategy);
            Frame lastFrame = frames.getLastCompletedFrame(frame);

            gameHistories.addHistory(GameHistory.Builder()
                    .pitchesPoint(pitches)
                    .player(playerDTO.getPlayer())
                    .currentFrameNo(lastFrame.getFrameNo() + PLUS_NUMBER)
                    .marks(frames.getMarks(frame))
                    .points(frames.getScores(frame))
                    .build());

            loop = !lastFrame.isFinished();
        }
        return gameHistories;
    }
}
