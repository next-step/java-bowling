package bowling.controller;

import bowling.domain.pin.Pin;
import bowling.domain.player.Player;

import java.util.function.Consumer;
import java.util.function.Function;

public final class BowlingTemplate {

    private final Function<Player, Pin> beforeBowl;
    private final Runnable afterBowl;

    public BowlingTemplate(Function<Player, Pin> beforeBowl, Runnable afterBowl) {
        this.beforeBowl = beforeBowl;
        this.afterBowl = afterBowl;
    }

    public void execute(Player player, Consumer<Pin> consumer) {
        Pin pin = beforeBowl.apply(player);
        consumer.accept(pin);
        afterBowl.run();
    }
}
