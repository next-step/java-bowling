package domain.state.open;

import domain.state.State;

abstract class Open implements State {

    @Override
    public boolean isClosed() {
        return Boolean.FALSE;
    }
}
