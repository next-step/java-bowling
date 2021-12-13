package bowling.domain.bowl;

public class StrikeBowl extends FinishedBowl {

    private static final StrikeBowl CACHED_BOWL = new StrikeBowl();

    private StrikeBowl() {
    }

    public static Bowl bowl() {
        return CACHED_BOWL;
    }

    @Override
    public String getView() {
        return "  X   ";
    }
}
