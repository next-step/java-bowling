package bowling.domain.state;

public class Strike extends Finished{
    private static final String STRIKE_MARK = "X";

    @Override
    public String getMark() {
        return STRIKE_MARK;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return true;
    }
}
