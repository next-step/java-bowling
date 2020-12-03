package bowling.domain.member;

import bowling.domain.member.exception.InvalidMemberNameLengthException;

public class MemberName {
    private static final int FIXED_NAME_LENGTH = 3;
    private final String name;

    private MemberName(String name) {
        this.name = name;
    }

    public static MemberName of(String name) {
        validateNameLength(name.length());
        return new MemberName(name);
    }

    private static void validateNameLength(int length) {
        if (length != FIXED_NAME_LENGTH) {
            throw new InvalidMemberNameLengthException();
        }
    }

    public String getName() {
        return name;
    }
}
