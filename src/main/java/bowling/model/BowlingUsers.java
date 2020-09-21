package bowling.model;

import bowling.exception.NotFoundBowlingUserException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BowlingUsers {

    private final List<User> users;
    private int playFrameNo;

    private BowlingUsers(List<User> users, int playFrameNo) {
        this.users = users;
        this.playFrameNo = playFrameNo;
    }

    public static BowlingUsers of(List<String> userNames) {
        return userNames.stream()
                        .map(User::valueOf)
                        .collect(Collectors.collectingAndThen(Collectors.toList(), users -> new BowlingUsers(users, 1)));
    }

    public void bowling(int countOfPins) {
        getTurnToUser().bowling(countOfPins);

        if (isEndFrames(playFrameNo)) {
            playFrameNo++;
        }

    }
    private User getTurnToUser() {
        return users.stream()
                .filter(user -> !user.isEndOf(playFrameNo))
                .findFirst().orElseThrow(NotFoundBowlingUserException::new);
    }

    public String getTurnUserName() {
        return getTurnToUser().getName();
    }

    private boolean isEndFrames(int playFrameNo) {
        return getBowlingGames()
                .allMatch(bowlingGame -> bowlingGame.isEndOf(playFrameNo));
    }

    public boolean isEnd() {
        return getBowlingGames()
                .allMatch(BowlingGame::isEnd);
    }

    public Stream<User> getUsers() {
        return users.stream();
    }

    private Stream<BowlingGame> getBowlingGames() {
        return users.stream()
                .map(User::getBowlingGame);
    }

}
