package bowling.domain.play;

import bowling.domain.RollSubject;
import bowling.domain.Rolls;
import bowling.dto.RollsDto;

public class PlayContext {
    private final RollSubject subject;
    private PlayState state = NormalPlayState.getInstance();

    public PlayContext(RollSubject subject) {
        this.subject = subject;
    }

    public void register(Runnable runnable) {
        subject.register(runnable);
    }

    void setState(PlayState state) {
        this.state = state;
    }

    void execute() {
        subject.execute();
    }

    public void play(int frameNo) {
        state.playFirst(this);
        state.playSecond(this, frameNo);
        state.playBonus(this);
    }

    int getCountOfPins(int countOfRolls) {
        Rolls rolls = subject.get();
        int rollIndex = rolls.size() - countOfRolls;
        return rolls.sum(rollIndex, countOfRolls);
    }

    public RollsDto exportRollsDto() {
        return subject.exportRollsDto();
    }
}
