package bowling.infra;

import bowling.domain.Frames;
import bowling.domain.Participant;

import java.util.LinkedHashMap;
import java.util.Map;

public class BowlingGameDatabase {
    public static Map<Participant, Frames> bowlingGameData = new LinkedHashMap<>();
}
