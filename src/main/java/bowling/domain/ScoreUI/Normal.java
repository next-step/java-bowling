package bowling.domain.ScoreUI;

public class Normal implements ScoreFactory{

    private String uiString;

    public Normal(String uiString) {
        this.uiString = uiString;
    }

    @Override
    public String getString() {
        return this.uiString;
    }
}
