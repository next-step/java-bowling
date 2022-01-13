package bowling.state.ended;

public class Strike extends Ended {

    @Override
    public String symbol() {
        return "X";
    }

    @Override
    public boolean isMiss() {
        return false;
    }
}
