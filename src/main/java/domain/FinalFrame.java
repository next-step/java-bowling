package domain;

public class FinalFrame implements Frame {

    private State state;
    private State bonusState; //TODO: bonusBowl 처리 방법 고민

    private FinalFrame() {
        this.state = new StandBy();
        this.bonusState = null;
    }

    public static FinalFrame of() {
        return new FinalFrame();
    }

    @Override
    public Frame fillFrame(Pins fallenPins) {
        state.update(fallenPins);
        return this;
    }
}
