package bowling.domain.frame;

import bowling.domain.ResultMark;
import bowling.domain.exception.EndedFrameException;
import bowling.domain.pin.FallenPins;

public class FinalFrame implements Frame {

    private FallenPins firstFallenPins;
    private FallenPins secondFallenPins;
    private FallenPins thirdFallenPins;

    @Override
    public Frame updateFrameState(FallenPins fallenPins) {
        if (isFinish()) {
            throw new EndedFrameException();
        }

        if (firstFallenPins == null) {
            firstFallenPins = fallenPins;
            return this;
        }

        if (secondFallenPins == null) {
            secondFallenPins = fallenPins;
            return this;
        }

        thirdFallenPins = fallenPins;
        return this;
    }

    @Override
    public String getResult() {
        ResultMark firstResultMark = ResultMark.getResultMark(firstFallenPins, null);
        ResultMark secondResultMark = ResultMark.getResultMark(secondFallenPins, firstFallenPins);
        ResultMark thirdResultMark = ResultMark.getResultMark(thirdFallenPins, null);

        String firstResult = getMark(firstResultMark, firstFallenPins);
        String secondResult = getMark(secondResultMark, secondFallenPins);
        String thirdResult = getMark(thirdResultMark, thirdFallenPins);

        String result = firstResult + RESULT_DELIMITER + secondResult + RESULT_DELIMITER + thirdResult;
        return result.replace(RESULT_DELIMITER + ResultMark.EMPTY.getMark(), ResultMark.EMPTY.getMark());
    }

    @Override
    public boolean isFinish() {
        if (hasBonus()) {
            return isNormalRoundFinish() && thirdFallenPins != null;
        }
        return isNormalRoundFinish();
    }

    private boolean isNormalRoundFinish() {
        return firstFallenPins != null && secondFallenPins != null;
    }

    private boolean hasBonus() {
        if (isHasStrike()) {
            return true;
        }
        return isHasSpare();
    }

    private boolean isHasStrike() {
        if (firstFallenPins != null
                && ResultMark.STRIKE.equals(ResultMark.getResultMark(firstFallenPins, null))) {
            return true;
        }
        return secondFallenPins != null
                && ResultMark.STRIKE.equals(ResultMark.getResultMark(secondFallenPins, null));
    }

    private boolean isHasSpare() {
        return ResultMark.SPARE.equals(ResultMark.getResultMark(secondFallenPins, firstFallenPins));
    }

    private String getMark(ResultMark resultMark, FallenPins fallenPins) {
        if (ResultMark.MISS.equals(resultMark)) {
            return String.valueOf(fallenPins.getCountOfPin());
        }
        return resultMark.getMark();
    }

}
