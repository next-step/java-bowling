package bowling.domain.core;

public final class RolledResultFactory {

    private static final RolledResultFactory rolledResultFactory = new RolledResultFactory();
    private final TowFallenPins towFallenPins;

    private RolledResultFactory() {
        towFallenPins = TowFallenPins.mutable();
    }

    static RolledResultFactory of(){
        rolledResultFactory.towFallenPins.initTowFallenPins();
        return rolledResultFactory;
    }

    public static RolledResultFactory collectPins(int fallenPins) {
        return rolledResultFactory.collect(Pins.of(fallenPins));
    }

    RolledResultFactory collect(Pins fallenPins){
        towFallenPins.collect(fallenPins);
        return this;
    }

    public RolledResult toRolledResult() {
        return towFallenPins.toRolledResult();
    }
}
