package bowling.controller;

import bowling.model.Bowlings;
import bowling.model.Pins;
import bowling.utility.Assert;
import bowling.view.InputView;
import bowling.view.ResultView;
import bowling.view.dto.BowlingResponse;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class BowlingGame {

    private final InputView inputView;
    private final ResultView resultView;

    private BowlingGame(InputStream inputStream, PrintStream printStream) {
        Assert.notNull(inputStream, "inputStream must not be null");
        Assert.notNull(printStream, "printStream must not be null");
        this.inputView = InputView.of(new Scanner(inputStream), printStream);
        this.resultView = ResultView.from(printStream);
    }

    public static BowlingGame of(InputStream inputStream, PrintStream printStream) {
        return new BowlingGame(inputStream, printStream);
    }

    public void start() {
        Bowlings bowlings = Bowlings.fromNames(inputView.participants());
        while (bowlings.isNotFinished()) {
            bowlings.pitch(nextPins(bowlings));
            resultView.print(BowlingResponse.listFrom(bowlings));
        }
    }

    private Pins nextPins(Bowlings bowlings) {
        return Pins.from(inputView.nextPitch(bowlings.nextParticipant().name()));
    }
}
