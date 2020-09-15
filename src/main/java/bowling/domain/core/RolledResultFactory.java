package bowling.domain.core;

public final class RolledResultFactory {

    private static final RolledResultFactory rolledResultFactory = new RolledResultFactory();

    private static final RolledResult NOT_AT_ROLLED_RESULT = new NotAtRolledResult();

    public static RolledResult notAtRolledResult() {
        return NOT_AT_ROLLED_RESULT;
    }

    private final TwoFallenPins towFallenPins;

    private RolledResultFactory() {
        towFallenPins = TwoFallenPins.mutable();
    }

    public static void init(){
        rolledResultFactory.towFallenPins.initTwoFallenPins();
    }

    static RolledResultFactory of(){
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
