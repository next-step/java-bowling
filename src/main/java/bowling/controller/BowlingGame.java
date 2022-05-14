package bowling.controller;

import bowling.model.Bowling;
import bowling.model.Participant;
import bowling.model.Pins;
import bowling.utility.Assert;
import bowling.view.InputView;
import bowling.view.ResultView;
import bowling.view.dto.FramesResponse;

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
        Bowling bowling = Bowling.from(Participant.from(inputView.participant()));
        while (bowling.isNotFinished()) {
            bowling = bowling.pitch(nextPins(bowling));
            resultView.print(bowling.participant().name(), FramesResponse.from(bowling.frames()));
        }
    }

    private Pins nextPins(Bowling bowling) {
        return Pins.from(inputView.nextPitch(bowling.nextFrameNumber().toInt()));
    }
}
