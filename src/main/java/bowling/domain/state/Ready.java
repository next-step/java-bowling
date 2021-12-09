package bowling.domain.state;

import bowling.domain.frame.Pin;

public class Ready extends Running {

    private Ready() {
    }

    public static Ready getInstance() {
        return InnerInstanceClazz.instance;
    }

    @Override
    public State bowl(Pin pin) {
        if (pin.isMaxCount()) {
            return new Strike();
        }

        return new FirstBowl(pin);
    }

    private static class InnerInstanceClazz {
        private static final Ready instance = new Ready();
    }
}
