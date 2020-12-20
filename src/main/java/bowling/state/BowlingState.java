package bowling.state;

/**
 * Created By mand2 on 2020-12-19.
 */
public interface BowlingState {

    boolean isStrike();

    boolean isSpare();

    boolean isPlayable();

    boolean isFinalPlayable();

    String printResult();
}
