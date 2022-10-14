package bowling.step2.domain.state;

abstract public class Running implements State {
    @Override
    public boolean isFinished() {
        return false;
    }
    
    @Override
    public boolean isSpare() {
        return false;
    }
    
    @Override
    public boolean isStrike() {
        return false;
    }
}
