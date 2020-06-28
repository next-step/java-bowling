package bowling.domain.ScoreUI;

public class Spare implements ScoreFactory{

    private static final String uiString = "/";
    @Override
    public String getString() {
        return uiString;
    }

}
