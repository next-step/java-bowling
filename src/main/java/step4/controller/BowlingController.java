package step4.controller;

import step4.domain.*;
import step4.exception.InvalidPitchesException;
import step4.view.ConsoleViewImpl;
import step4.view.InputView;
import step4.view.ResultView;
import step4.view.View;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BowlingController {
    private final View view;

    public BowlingController(ResultView resultView, InputView inputView) {
        this.view = new ConsoleViewImpl(inputView, resultView);
    }

    public void gameStart() {
        int participantsCount = view.getNumberOfParticipants();
        Players players = getPlayers(participantsCount);

        view.drawEmptyLine(players.names());

        pitches(players);

    }

    private Players getPlayers(int participantsCount) {
        return IntStream.range(0, participantsCount)
                .mapToObj(index -> {
                    String playerName = view.getPlayerName(index + 1);
                    return Player.of(playerName);
                }).collect(Collectors.collectingAndThen(Collectors.toList(), Players::new));
    }

    private void pitches(Players players) {
        try {
            loopPitches(players);
        } catch (InvalidPitchesException | IllegalArgumentException error) {
            System.out.println(error.getMessage());
            pitches(players);
        }
    }

    private void loopPitches(Players players) {
        while (!players.isAllFinished()) {
            players.filteredForEach(isFinishedCondition(), pitchesAndDrawConsumer(players));
        }
    }

    private Predicate<Player> isFinishedCondition() {
        return player -> !player.isFinished();
    }

    private Consumer<Player> pitchesAndDrawConsumer(Players players) {
        return player -> {
            int pitchesCount = view.getPitchesCount(player);

            player.pitches(pitchesCount);

            GameHistories histories = players.createFramesHistories();

            view.drawFrame(histories);
        };
    }
}
