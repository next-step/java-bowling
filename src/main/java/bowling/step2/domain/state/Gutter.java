package bowling.step2.domain.state;

public class Gutter implements PitchStatus{
    @Override
    public String result(int count) {
        return "-";
    }
}
