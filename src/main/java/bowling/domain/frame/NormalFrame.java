package bowling.domain.frame;

import bowling.domain.FallenPins;
import bowling.domain.ResultMark;
import bowling.domain.exception.EndedFrameException;
import bowling.domain.exception.ExceedFallenPinsException;

public class NormalFrame implements Frame {

    private FallenPins firstFallenPins;
    private FallenPins secondFallenPins;

    @Override
    public NormalFrame updateFrameState(FallenPins fallenPins) {
        if (isFinish()) {
            throw new EndedFrameException();
        }

        if (firstFallenPins == null) {
            this.firstFallenPins = fallenPins;
            return this;
        }

        validateSecondFallenPins(fallenPins);

        this.secondFallenPins = fallenPins;

        return this;
    }

    @Override
    public String getResult() {
        ResultMark firstResultMark = ResultMark.getResultMark(firstFallenPins, null);
        ResultMark secondResultMark = ResultMark.getResultMark(secondFallenPins, firstFallenPins);

        String firstResult = getMark(firstResultMark, firstFallenPins);
        String secondResult = getMark(secondResultMark, secondFallenPins);
        if (!ResultMark.EMPTY.equals(secondResultMark)) {
            secondResult = RESULT_DELIMITER + secondResult;
        }

        return firstResult + secondResult;
    }

    @Override
    public boolean isFinish() {
        if (firstFallenPins == null) {
            return false;
        }
        return hasStrike()
                || secondFallenPins != null;
    }

    private boolean hasStrike() {
        return firstFallenPins.isAllPinFallen();
    }

    private void validateSecondFallenPins(FallenPins fallenPins) {
        if (firstFallenPins.getCountOfPin() + fallenPins.getCountOfPin()
                > FallenPins.MAX_COUNT_OF_PIN) {
            throw new ExceedFallenPinsException();
        }
    }

    private String getMark(ResultMark resultMark, FallenPins fallenPins) {
        if (ResultMark.MISS.equals(resultMark)) {
            return String.valueOf(fallenPins.getCountOfPin());
        }
        return resultMark.getMark();
    }

}
