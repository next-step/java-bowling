package bowling.controller;

import bowling.domain.Player;
import bowling.domain.Score;
import bowling.domain.ScoreType;
import bowling.domain.Subtotal;
import bowling.view.Input;
import bowling.view.Output;

import java.util.ListIterator;

public class BowlingApp {
    public static void main(String[] args) {
        String name = Input.scan();
        Player player = new Player(name);
        Output.printFrame(player);

        player.plays();
        Output.printFrames(player);
    }
}

    public void plays(Player player) {
        ListIterator<Score> iterator = this.scores.listIterator();
        while (iterator.hasNext()) {
            int i = iterator.nextIndex();
            Score score = this.scores.get(i);
            Score newScore = Score.play(score);
            this.scores.set(i, newScore);
            Subtotal prevSubtotal = this.subtotals.get(i > 0 ? i - 1 : 0);
            prevSubtotal.evaluateBonus(newScore);
            Output.printFrame(i + 1, player);
            if (newScore.done()) {
                Subtotal newSubtotal = Subtotal.create(newScore, i > 0 ? prevSubtotal.score() : 0);
                this.subtotals.set(i, newSubtotal);
                iterator.next();
            }
        }
        handleLast(player);
//        return new Player(player.name(), this);
    }

    private void handleLast(Player player) {
        Subtotal prevSubtotal = this.subtotals.get(9);
        prevSubtotal.setLast();
        Score tenthScore = this.scores.get(9);
        if (tenthScore.scoreType() == ScoreType.STRIKE || tenthScore.scoreType() == ScoreType.SPARE) {
            Score finalScore = Score.playBonus(tenthScore);
            this.scores.set(9, finalScore);
            Subtotal newSubtotal = Subtotal.create(finalScore, prevSubtotal.score());
            this.subtotals.set(9, newSubtotal);
            Output.printFrame(11, player);
            return;
        }
        Output.printFrame(10, player);
    }