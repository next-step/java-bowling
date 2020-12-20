package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class LastFrame {

    private static final int FIRST_TRY_INDEX = 0;
    private static final int SECOND_TRY_INDEX = 1;

    private final List<DownedPin> tries;
    private boolean isEnded;

    public LastFrame() {
        tries = new ArrayList<>();
        isEnded = false;
    }

    public boolean isEnded() {
        return isEnded;
    }

    public void record(DownedPin currentTurn) {
        if (isEnded()) {
            return;
        }

        if (tries.size() == FIRST_TRY_INDEX) {
            tries.add(currentTurn);
            return;
        }

        if (tries.size() == SECOND_TRY_INDEX) {
            handleSecondTry(currentTurn);
            return;
        }

        handleThirdTurn(currentTurn);
    }

    private void handleSecondTry(DownedPin secondTry) {
        DownedPin firstTry = tries.get(FIRST_TRY_INDEX);

        if (firstTry.isStrike()) {
            tries.add(secondTry);
            return;
        }

        tries.add(firstTry.fromSubordinateTry(secondTry));

        if (!firstTry.isSpare(secondTry)) {
            isEnded = true;
        }
    }

    private void handleThirdTurn(DownedPin thirdTry) {
        isEnded = true;

        DownedPin firstTry = tries.get(FIRST_TRY_INDEX);
        DownedPin secondTry = tries.get(SECOND_TRY_INDEX);

        if (firstTry.isSpare(secondTry) || secondTry.isStrike()) {
            tries.add(thirdTry);
            return;
        }

        tries.add(secondTry.fromSubordinateTry(thirdTry));
    }

    public boolean isStrike(int index) {
        if (index >= tries.size()) {
            return false;
        }

        return tries.get(index).isStrike();
    }

    public boolean isSpare(int index) {
        if (index >= tries.size() || index < 1) {
            return false;
        }

        return tries.get(index - 1).isSpare(tries.get(index));
    }
}
