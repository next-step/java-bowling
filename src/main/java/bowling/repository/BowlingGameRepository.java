package bowling.repository;

import bowling.domain.Frames;
import bowling.domain.Participant;
import bowling.infra.BowlingGameDatabase;

public class BowlingGameRepository {

    public Frames findByParticipant(Participant participant) {
        return BowlingGameDatabase.bowlingGameData.get(participant);
    }

    public void save(Participant participant, Frames frames) {
        BowlingGameDatabase.bowlingGameData.put(participant, frames);
    }
}
