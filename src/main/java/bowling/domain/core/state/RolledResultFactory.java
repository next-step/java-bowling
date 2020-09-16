package bowling.domain.core.state;

import bowling.domain.core.FallenPins;
import bowling.domain.core.RolledResult;

public final class RolledResultFactory {

    private static final RolledResultFactory rolledResultFactory = new RolledResultFactory();

    private final TwoFallenPins towFallenPins;

    private RolledResultFactory() {
        towFallenPins = TwoFallenPins.mutable();
    }

    public static void init(){
        rolledResultFactory.towFallenPins.initTwoFallenPins();
    }

    public static RolledResultFactory of(){
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
