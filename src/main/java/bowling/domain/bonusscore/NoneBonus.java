package bowling.domain.bonusscore;

public class NoneBonus implements BonusScore {
    @Override
    public void add(int score) {
    }

    @Override
    public boolean isAddable() {
        return false;
    }
}
