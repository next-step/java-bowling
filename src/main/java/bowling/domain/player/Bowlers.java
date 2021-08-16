package bowling.domain.player;

import bowling.domain.dto.BowlerData;
import bowling.exception.NullArgumentException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Bowlers {
    private static final int IDX_OF_FIRST_PLAYER = 0;

    private final List<Bowler> bowlers;

    private Bowlers(List<String> names) {
        validate(names);

        bowlers = names.stream()
                .map(Bowler::of)
                .collect(Collectors.toList());
    }

    private void validate(List<String> names) {
        if (Objects.isNull(names)) {
            throw new NullArgumentException("Names list");
        }
    }

    public static Bowlers of(List<String> names) {
        return new Bowlers(names);
    }

    public Bowler firstBowler() {
        return bowlers.get(IDX_OF_FIRST_PLAYER);
    }

    public Bowler changeBowler(Bowler currentPlayer) {
        int indexOfCurrentPlayer = bowlers.indexOf(currentPlayer);
        int indexOfNextPlayer = (indexOfCurrentPlayer + 1) % bowlers.size();

        return bowlers.get(indexOfNextPlayer);
    }

    public List<BowlerData> bowlerData() {
        return bowlers.stream()
                .map(BowlerData::of)
                .collect(Collectors.toList());
    }

    public boolean isAllBowlerFinish() {
        return bowlers.stream().allMatch(Bowler::isBowlingFinish);
    }

}
