package bowling.domain;

import bowling.dto.RollsDto;

import java.util.function.Supplier;

public class RollSubject extends Subject<Rolls> {
    private final Rolls rolls = new Rolls();
    private final Supplier<Roll> supplier;

    public RollSubject(Supplier<Roll> supplier) {
        this.supplier = supplier;
    }

    @Override
    public Rolls get() {
        return rolls;
    }

    @Override
    public void execute() {
        rolls.add(supplier.get());
        notifyObservers();
    }

    public RollsDto exportRollsDto() {
        return rolls.exportRollsDto();
    }
}
