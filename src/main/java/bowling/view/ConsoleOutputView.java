package bowling.view;

import bowling.domain.Game;
import bowling.domain.Pitches;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static bowling.controller.BowlingGame.*;
import static bowling.domain.Pitch.*;
import static bowling.domain.Pitches.NUMBER_OF_PITCH_FOR_BEGINNING;
import static bowling.domain.Pitches.NUMBER_OF_PITCH_FOR_STRIKE;

public class ConsoleOutputView implements OutputView {
    private static final int FIRST_PIN = 0;
    private static final int SECOND_PIN = 1;

    private static final char SPACE = ' ';
    private static final char BAR = '|';
    private static final char SPARE_INDICATOR = '/';
    private static final char STRIKE_INDICATOR = 'X';
    private static final char GUTTER_INDICATOR = '-';
    private static final char ZERO = '0';

    private static final String EMPTY_FRAME = "|        ";
    private static final String STAGES = "|   %2d   ";
    private static final String NAME_TITLE = "|   NAME   ";
    private static final String NAME_FIELD = "|    %s   ";
    private static final String PIN_FIELD = "|   %c%c%c  ";

    @Override
    public void resultsOfBowling(Game game) {
        stagesOfBowling();
        framesOfBowling(game);
    }

    private void stagesOfBowling() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(NAME_TITLE);
        IntStream.rangeClosed(FIRST_FRAME, LAST_FRAME)
                .forEach(i -> stringBuilder.append(String.format(STAGES, i)));
        stringBuilder.append(BAR);

        System.out.println(stringBuilder);
    }

    private void framesOfBowling(Game game) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(NAME_FIELD, game.getName()));
//        List<Pitches> entirePinNumbers = game.pinNumbersPerFrame();

//        entirePinNumbers.forEach(pinNumbers -> {
//                    List<Integer> pins = pinNumbers.info();
//                    stringBuilder.append(setFrame(pins, pinNumbers.sum().getNumber()));
//                });

        IntStream.rangeClosed(FIRST_FRAME, LAST_FRAME - game.frameNumber())
                        .forEach(i -> stringBuilder.append(fillEmptyFrame()));
        stringBuilder.append(BAR);

        System.out.println(stringBuilder);
    }

    private StringBuilder fillEmptyFrame() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(EMPTY_FRAME);

        return stringBuilder;
    }

    private StringBuilder setFrame(List<Integer> pins, int pinNumbers) {
        StringBuilder stringBuilder = new StringBuilder();
        List<Character> convertedPins = new ArrayList<>();
        pins.forEach(pin -> convertedPins.add(convertIntegerToPin(pin)));

        if (convertedPins.size() == NUMBER_OF_PITCH_FOR_BEGINNING) {
            convertedPins.add(SPACE);
            convertedPins.add(SPACE);
        }

        if (convertedPins.size() == NUMBER_OF_PITCH_FOR_STRIKE) {
            convertedPins.add(SPACE);
        }

        stringBuilder.append(String.format(PIN_FIELD,
                convertedPins.get(FIRST_PIN),
                addSeparator(convertedPins.get(SECOND_PIN)),
                convertSpare(convertedPins.get(SECOND_PIN), pinNumbers)
        ));

        return stringBuilder;
    }

    private char convertIntegerToPin(int value) {
        if (value == STRIKE_PIN_NUMBER) {
            return STRIKE_INDICATOR;
        }

        if (value == GUTTER_PIN_NUMBER) {
            return GUTTER_INDICATOR;
        }

        return (char) (ZERO + value);
    }

    private char addSeparator(Character c) {
        if (!c.equals(SPACE)) {
            return BAR;
        }

        return c;
    }

    private char convertSpare(Character c, int totalScore) {
        if (totalScore == SPARE_PIN_NUMBER && !c.equals(SPACE)) {
            return SPARE_INDICATOR;
        }

        return c;
    }
}
