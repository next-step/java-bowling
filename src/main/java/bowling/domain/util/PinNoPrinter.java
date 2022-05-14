package bowling.domain.util;

import bowling.domain.pin.PinNo;
import bowling.domain.frameresult.Spare;

import java.util.List;

public class PinNoPrinter {

    private PinNoPrinter() {
    }

    public static String print(List<PinNo> pinNos) {
        PinNo firstNo = pinNos.get(0);
        if (pinNos.size() == 1) {
            return toExpression(firstNo);
        }

        PinNo secondNo = pinNos.get(1);
        if (pinNos.size() == 2) {
            return print(firstNo, secondNo);
        }

        return print(firstNo, secondNo) + "|" + toExpression(pinNos.get(2));
    }

    public static String print(PinNo firstNo, PinNo secondNo) {
        if (secondNo == null) {
            return toExpression(firstNo);
        }
        if (firstNo.plus(secondNo) instanceof Spare) {
            return toExpression(firstNo) + "|/";
        }
        return toExpression(firstNo) + "|" + toExpression(secondNo);
    }

    private static String toExpression(PinNo pinNo) {
        if (pinNo.isMaxNo()) {
            return "X";
        }
        if (pinNo.isMinNo()) {
            return "-";
        }
        return String.valueOf(pinNo.getNo());
    }
}
