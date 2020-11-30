package bowling.domain;

import bowling.dto.RollsDto;

import java.util.function.Supplier;

class RollSubject extends Subject<Rolls> {
    private final Rolls rolls = new Rolls();
    private final Supplier<Roll> strategy;

    RollSubject(Supplier<Roll> strategy) {
        this.strategy = strategy;
    }


    @Override
    Rolls get() {
        return rolls;
    }

    @Override
    void execute() {
        rolls.add(strategy.get());
        notifyObservers();
    }

    void executeZero() {
        rolls.add(Roll.of(0));
        notifyObservers();
    }

    RollsDto exportRollsDto() {
        return rolls.exportRollsDto();
    }
}
