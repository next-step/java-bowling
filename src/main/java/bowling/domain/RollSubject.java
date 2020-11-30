package bowling.domain;

import bowling.dto.RollsDto;

import java.util.function.Supplier;

class RollSubject extends Subject<Rolls> {
    private final Rolls rolls = new Rolls();
    private final Supplier<Roll> supplier;

    RollSubject(Supplier<Roll> supplier) {
        this.supplier = supplier;
    }

    @Override
    Rolls get() {
        return rolls;
    }

    @Override
    void execute() {
        rolls.add(supplier.get());
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
