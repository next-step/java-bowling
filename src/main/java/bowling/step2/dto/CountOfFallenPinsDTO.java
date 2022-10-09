package bowling.step2.dto;

import bowling.step2.domain.exception.CountOfFallenPinsException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountOfFallenPinsDTO {
    private static final String COUNT_OF_FALLEN_PINS_INPUT_FORMAT = "[1-9]|10";
    
    private final int countOfFallenPins;
    
    public CountOfFallenPinsDTO(final String countOfFallenPins) {
        this.countOfFallenPins = Integer.parseInt(checkCountOfFallenPinsFormat(countOfFallenPins));
    }
    
    private String checkCountOfFallenPinsFormat(final String countOfFallenPins) {
        final Matcher matcher = Pattern.compile(COUNT_OF_FALLEN_PINS_INPUT_FORMAT).matcher(countOfFallenPins);
        if (!matcher.matches()) {
            throw new CountOfFallenPinsException();
        }
        
        return countOfFallenPins;
    }
    
    public int getCountOfFallenPins() {
        return countOfFallenPins;
    }
}
