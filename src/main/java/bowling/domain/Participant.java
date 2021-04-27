package bowling.domain;

import common.utils.StringUtils;

import java.util.Objects;
import java.util.regex.Pattern;

public class Participant {

    private static final int NAME_LENGTH = 3;

    private final String name;

    public Participant(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("참가자명을 입력해 주세요.");
        }
        if (name.length() != NAME_LENGTH) {
            throw new IllegalArgumentException("참가자명은 "+ NAME_LENGTH + " 자리 이어야 합니다.");
        }
        if (!isAlphabet(name)) {
            throw new IllegalArgumentException("참가자명은 알파벳으로 구성되어야 합니다.");
        }
    }

    private static boolean isAlphabet(String participantName) {
        return Pattern.matches("^[a-zA-Z]*$", participantName);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
