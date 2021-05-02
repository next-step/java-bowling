package bowling.repository;

import bowling.domain.Frames;
import bowling.domain.Participant;
import bowling.infra.BowlingGameDatabase;

import java.util.Map;
import java.util.Optional;

public class BowlingGameRepository {

    public Optional<Frames> findByParticipant(Participant participant) {
        return Optional.ofNullable(BowlingGameDatabase.bowlingGameData.get(participant));
    }

    public void save(Participant participant, Frames frames) {
        BowlingGameDatabase.bowlingGameData.put(participant, frames);
    }

    public Map<Participant, Frames> findAllFrames() {
        return BowlingGameDatabase.bowlingGameData;
    }
}
