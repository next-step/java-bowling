package bowling.dto;

import bowling.domain.Player;
import bowling.domain.Players;
import bowling.domain.frame.Frames;

import java.util.List;
import java.util.stream.Collectors;

public class BowlingRecord {

    private final int nth;
    private final String playerName;
    private final List<FrameRecord> frameRecords;

    public BowlingRecord(int nth, String playerName, List<FrameRecord> frameRecords) {
        this.nth = nth;
        this.playerName = playerName;
        this.frameRecords = frameRecords;
    }

    public static BowlingRecord of(Player player) {
        List<FrameRecord> records = getFrameRecords(player.getFrames());
        return new BowlingRecord(player.nth(), player.getPlayerName(), records);
    }

    public static List<BowlingRecord> ofList(Players players){
        return  players.getPlayers()
                .stream()
                .map(BowlingRecord::of)
                .collect(Collectors.toUnmodifiableList());
    }

    private static List<FrameRecord> getFrameRecords(Frames frames) {

        return frames.getFrames().stream()
                .map(FrameRecord::of)
                .collect(Collectors.toUnmodifiableList());

    }

    public String getPlayerName() {
        return playerName;
    }

    public List<FrameRecord> getFrameRecords() {
        return frameRecords;
    }

    public int getNth() {
        return nth;
    }
}
