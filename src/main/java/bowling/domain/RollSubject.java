package bowling.domain;

import java.util.function.Supplier;

public class RollSubject extends Subject<Rolls> {
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
}
