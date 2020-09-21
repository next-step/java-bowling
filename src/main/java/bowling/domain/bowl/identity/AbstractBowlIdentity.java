package bowling.domain.bowl.identity;

import bowling.domain.bowl.BowlResult;

import java.util.HashMap;
import java.util.Map;

import static bowling.domain.NumberOfPin.MAX_NUMBER_OF_PIN;
import static bowling.domain.NumberOfPin.MIN_NUMBER_OF_PIN;

public abstract class AbstractBowlIdentity implements BowlIdentity {

    protected static final int FIRST_BOWL = 1;
    protected static final int SECOND_BOWL = 2;
    private static final Map<String, String> MARK = new HashMap<>();

    static {
        MARK.put(String.valueOf(MIN_NUMBER_OF_PIN), "-");
        MARK.put(String.valueOf(MAX_NUMBER_OF_PIN), "X");
    }

    @Override
    public String format(BowlResult bowlResult) {
        String message = message(bowlResult);
        for (Map.Entry<String, String> element : MARK.entrySet()) {
            message = message.replaceAll(element.getKey(), element.getValue());
        }
        return message;
    }

    protected abstract String message(BowlResult bowlResult);

}
