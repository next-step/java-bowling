package bowling.step2.dto;

import bowling.step2.domain.exception.CountOfFallenPinsException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountOfFallenPinsDTO {
    private final int countOfFallenPins;
    
    public CountOfFallenPinsDTO(final String countOfFallenPins) {
        this.countOfFallenPins = Integer.parseInt(checkCountOfFallenPinsFormat(countOfFallenPins));
    }
    
    private String checkCountOfFallenPinsFormat(final String countOfFallenPins) {
        final Matcher matcher = Pattern.compile("[1-9]|10").matcher(countOfFallenPins);
        if (!matcher.matches()) {
            throw new CountOfFallenPinsException();
        }
        return null;
    }
}
