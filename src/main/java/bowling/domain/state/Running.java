package bowling.domain.state;

public abstract class Running implements State {

    @Override
    public final boolean isEnd(){
        return false;
    }
}
