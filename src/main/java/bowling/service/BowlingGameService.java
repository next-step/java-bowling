package bowling.service;

import bowling.domain.Frames;
import bowling.domain.Participant;
import bowling.repository.BowlingGameRepository;

public class BowlingGameService {

    private final BowlingGameRepository repository;

    public BowlingGameService() {
        repository = new BowlingGameRepository();
    }

    public void startGame(Participant participant) {
        repository.save(participant, new Frames());
    }

    public void pitchBall(Participant participant, int pinDownCount) {
        Frames frames = repository.findByParticipant(participant);
        frames.pitch(pinDownCount);
    }

    public Frames findFrames(Participant participant) {
        return repository.findByParticipant(participant);
    }
}
