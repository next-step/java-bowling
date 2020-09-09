package bowling.domain.core;

public final class RolledResultFactory {

    private static final RolledResultFactory rolledResultFactory = new RolledResultFactory();
    private final TwoFallenPins towFallenPins;

    private RolledResultFactory() {
        towFallenPins = TwoFallenPins.mutable();
    }

    static RolledResultFactory of(){
        rolledResultFactory.towFallenPins.initTowFallenPins();
        return rolledResultFactory;
    }

    public static RolledResultFactory collectPins(int fallenPins) {
        return rolledResultFactory.collect(FallenPins.of(fallenPins));
    }

    RolledResultFactory collect(FallenPins fallenPins){
        towFallenPins.collect(fallenPins);
        return this;
    }

    public RolledResult toRolledResult() {
        return towFallenPins.toRolledResult();
    }
}
