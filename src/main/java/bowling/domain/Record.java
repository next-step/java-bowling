package bowling.domain;

import java.util.List;

public class Record {
    private final Frames frames;
    private final Player player;

    public Record(Player player) {
        this.frames = new Frames();
        this.player = player;
    }

    public String getPlayerName() {
        return player.getName();
    }

    public void record(int downPintCount) {
        frames.record(downPintCount);
    }

    public List<Frame> getFrames() {
        return frames.getFrames();
    }

    public boolean isEndFrame(int frameIndex) {
        return frames.isEndFrame(frameIndex);
    }

    public int getRecordCount() {
        return frames.getRecordCount();
    }

    public boolean isEndLastFrame() {
        return frames.isEndLastFrame();
    }

    public int getTotalScore() {
        return frames.getTotalScore();
    }

    public int getTotalScore(int targetIndex) {
        return frames.getTotalScore(targetIndex);
    }

    public boolean isReadyFrameScore(int frameIndex) {
        return frames.isReadyFrameScore(frameIndex);
    }

    public boolean isEndRecord() {
        return frames.isEndRecord();
    }
}
