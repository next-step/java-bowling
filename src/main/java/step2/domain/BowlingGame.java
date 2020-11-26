package step2.domain;

import step2.domain.dto.PlayerDTO;
import step2.strategy.BowlingPitchesStrategy;

import java.util.ArrayList;

public class BowlingGame {

    public static final int FRAME_FIRST_NO = 0;
    public static final int FRAME_LAST_NO = 8;
    public static final int FRAME_SIZE = 10;

    public static Frames build() {
        return Frames.Builder()
                .size(FRAME_SIZE)
                .head(new NormalFrame(FRAME_FIRST_NO))
                .build();
    }

    public static GameHistories start(PlayerDTO playerDTO, BowlingPitchesStrategy strategy) {
        Frames frames = playerDTO.getFrames();
        Frame frame = frames.getHead();
        GameHistories gameHistories = new GameHistories();

        while (!frames.getLast().isFinished()) {
            int pitches = frame.pitches(strategy);
            Frame lastFrame = frames.getLastCompletedFrame(frame);

            gameHistories.addHistory(GameHistory.Builder()
                    .pitchesPoint(pitches)
                    .player(playerDTO.getPlayer())
                    .currentFrameNo(lastFrame.getFrameNo() + 1)
                    .points(frames.getScores(new ArrayList<>(), frame))
                    .build());
        }
        return gameHistories;
    }
}
