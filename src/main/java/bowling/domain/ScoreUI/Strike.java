package bowling.domain.ScoreUI;

public class Strike implements ScoreFactory {

    private static final String uiString = "x";
    @Override
    public String getString() {
        return uiString;
    }
}
