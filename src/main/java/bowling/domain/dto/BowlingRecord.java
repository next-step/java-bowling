package bowling.domain.dto;

import bowling.domain.Pin;
import bowling.domain.PlayerName;
import bowling.domain.frame.Frames;

import java.util.List;
import java.util.stream.Collectors;

public class BowlingRecord {
    private final int nth;
    private final int nowPin;
    private final String playerName;
    private final List<FrameRecord> frameRecords;

    public BowlingRecord(int nth, int nowPin, String playerName, List<FrameRecord> frameRecords) {
        this.nth = nth;
        this.nowPin = nowPin;
        this.playerName = playerName;
        this.frameRecords = frameRecords;
    }


    public static BowlingRecord of(Frames frames, Pin now, PlayerName playerName) {

        int frameNth = frames.getFrameNumber();

        List<FrameRecord> records = getFrameRecords(frames);

        return new BowlingRecord(frameNth, now.getValue(), playerName.toString(), records);
    }

    public static BowlingRecord of(Frames frames, PlayerName playerName) {
        int frameNth = frames.getFrameNumber();

        List<FrameRecord> records = getFrameRecords(frames);

        return new BowlingRecord(frameNth, Pin.NONE, playerName.toString(), records);
    }

    private static List<FrameRecord> getFrameRecords(Frames frames) {

        return frames.getFrames().stream()
                .map(FrameRecord::of)
                .collect(Collectors.toUnmodifiableList());

    }

    public int getNth() {
        return nth;
    }

    public int getNowPin() {
        return nowPin;
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<FrameRecord> getFrameRecords() {
        return frameRecords;
    }
}
