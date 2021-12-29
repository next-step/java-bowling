package bowling.domain.state;

import static java.lang.Boolean.FALSE;

public abstract class Running implements State {

    @Override
    public boolean isFinished() {
        return FALSE;
    }
    
}
