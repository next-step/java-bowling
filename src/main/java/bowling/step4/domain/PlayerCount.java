package bowling.step4.domain;

import bowling.step4.exception.PlayerMinimumCountException;

import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class PlayerCount {

    private final int count;

    private PlayerCount(int count) {
        this.count = count;
    }

    public static PlayerCount of(int count) {
        return new PlayerCount(count);
    }

    private static void validate(int count) {
        if (count < 0) {
            throw new PlayerMinimumCountException();
        }
    }

    public PlayersFrames ofPlayersFrames(Function<Integer, PlayerFrames> mapper) {
        return IntStream.rangeClosed(0, count)
                        .mapToObj(mapper::apply)
                        .collect(collectingAndThen(toList(), PlayersFrames::of));
    }

}