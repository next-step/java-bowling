package bowling.domain;

import bowling.util.ErrorMessage;

public class Name {

    private final int MAX_NAME_LENGTH = 3;
    private String name = "";

    public Name(final String name) {
        checkNameLength(name);
        checkNullOrEmpty(name);

        this.name = name;
    }

    private void checkNullOrEmpty(final String name) {
        if (name.trim().isEmpty() || name == null) {
            throw new NullPointerException(ErrorMessage.getCheckNameNullOrEmpty());
        }
    }

    private void checkNameLength(final String name){
        if(name.length() > MAX_NAME_LENGTH){
            throw new RuntimeException(ErrorMessage.getCheckNameLength());
        }
    }

    public String getName() {
        return name;
    }
}
