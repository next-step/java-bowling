package bowling.domain.state;

abstract public class Running implements Status {

    @Override
    public boolean isFinished() {
        return false;
    }

}
