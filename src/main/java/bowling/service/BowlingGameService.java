package bowling.service;

import bowling.domain.Frames;
import bowling.domain.Participant;
import bowling.repository.BowlingGameRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

public class BowlingGameService {

    private final BowlingGameRepository repository;

    public BowlingGameService() {
        repository = new BowlingGameRepository();
    }

    public void startGame(Participant participant) {
        Optional<Frames> frames = repository.findByParticipant(participant);
        if (frames.isPresent()) {
            throw new IllegalArgumentException("이미 등록한 참가자입니다.");
        }
        repository.save(participant, new Frames());
    }

    public void pitchBall(Participant participant, int pinDownCount) {
        Frames frames = findFrames(participant);
        frames.pitch(pinDownCount);
    }

    public Frames findFrames(Participant participant) {
        Optional<Frames> frames = repository.findByParticipant(participant);
        return frames.orElseThrow(() -> new NoSuchElementException("존재하지 않는 참가자입니다."));
    }
}
