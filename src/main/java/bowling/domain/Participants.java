package bowling.domain;

import bowling.domain.frame.Frame;

import java.util.List;

public class Participants {

    private final List<Player> players;

    private int currentPosition = 0;

    public Participants(List<Player> players) {
        this.players = players;
    }

    public boolean bowl(int numberOfPins) {
        Frame currentFrame = players.get(currentPosition).getFrame();
        Frame nextFrame = players.get(currentPosition).bowl(numberOfPins);
        if (nextFrame.isEnd() && isLastPlayer()) {
            return true;
        }
        if (currentFrame.isCompleted()) {
            movePosition();
        }
        return false;
    }

    private void movePosition() {
        if (isLastPlayer()) {
            currentPosition = 0;
            return;
        }
        currentPosition++;
    }

    private boolean isLastPlayer() {
        return currentPosition == players.size() - 1;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPosition);
    }

}
