package bowling.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Users {

    private static final int MAX_NUMBER = 6;

    private final Map<User, Frames> users;

    public Users(List<String> userNames) {
        users = new HashMap<>();
        userNames.stream()
                .map(User::new)
                .forEach(user -> users.put(user, new Frames()));

        isValidate();
    }

    public Map<User, Frames> getUsers() {
        return users;
    }

    public boolean hasRemainTurn(int frameNumber) {
        return users.values()
                .stream()
                .anyMatch(frame -> frame.hasRemainTurn(frameNumber));
    }

    public User currentTurnUser(int frameNumber) {
        return users.keySet()
                .stream()
                .filter(user -> users.get(user).hasRemainTurn(frameNumber))
                .findFirst()
                .get();
    }

    private void isValidate() {
        if (users.size() > MAX_NUMBER) {
            throw new IllegalArgumentException("한 레인의 최대 인원 수는 6인 입니다.");
        }
    }

    public void proceed(int frameNumber, User currentTurnUser, PinNumber pinNumber) {
        users.get(currentTurnUser).proceedRound(frameNumber, pinNumber);
    }
}