package bowling.domain;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class Bowlings {
    private final Map<Username, Bowling> bowlings;

    private final Usernames usernames;

    public Bowlings(List<Username> usernames) {
        this.bowlings = usernames.stream()
                .map((username) -> Map.entry(username, new Bowling()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
        this.usernames = new Usernames(usernames);
    }

    public boolean isFinish() {
        return bowlings.values()
                .stream()
                .allMatch(Bowling::isFinish);
    }

    public Username currentUser() {
        return usernames.current();
    }

    public ScoreResult play(Integer knockDownPinNumber) {
        Username currentUser = currentUser();
        Bowling bowling = bowlings.get(currentUser);
        BowlingRound before = bowling.currentRound();
        ScoreResult scoreResult = bowling.play(knockDownPinNumber);
        BowlingRound after = bowling.currentRound();
        if (isNextUserTurn(before, after)) {
            usernames.next();
        }
        scoreResult.addUsername(currentUser);
        return scoreResult;
    }

    private boolean isNextUserTurn(BowlingRound before, BowlingRound after) {
        return !after.isSameRound(before) || after.isLastRoundEnd();
    }


    public Bowling findBowlingByUsername(Username username) {
        return bowlings.get(username);
    }

    public Usernames getUsernames() {
        return usernames;
    }
}
