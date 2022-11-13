package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Usernames {

    private final List<Username> usernames;

    private int position;

    public Usernames(List<Username> usernames) {
        if (usernames == null || usernames.isEmpty()) {
            throw new IllegalArgumentException("사용자 이름은 최소한 한개 이상이여야만 합니다.");
        }
        this.usernames = new ArrayList<>(usernames);
        this.position = 0;
    }

    public Username current() {
        if (position >= usernames.size()) {
            return usernames.get(0);
        }
        return usernames.get(position);
    }

    public void next() {
        if (position >= usernames.size()) {
            this.position = 0;
        }
        this.position = position + 1;
    }

    public List<Username> getUsernames() {
        return new ArrayList<>(usernames);
    }
}
