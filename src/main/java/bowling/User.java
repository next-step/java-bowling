package bowling;

import static utils.StringUtils.isAllEnglishLetter;

public class User {
    private final static int NAME_LENGTH = 3;
    private final static String NAME_LENGTH_ERROR = "플레이어의 이름은 3자만 가능합니다.";
    private final static String NAME_LETTER_ERROR = "플레이어의 이름은 영어만 가능합니다.";

    private final String name;

    private User(String name){
        this.name = name;
    }

    public static User from(String name){
        validationName(name);

        return new User(name);
    }

    private static void validationName(String name) {
        if(name.length() != NAME_LENGTH){
            throw new IllegalArgumentException(NAME_LENGTH_ERROR);
        }

        if(!isAllEnglishLetter(name)){
            throw new IllegalArgumentException(NAME_LETTER_ERROR);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof User){
            return name.equals(((User) obj).name);
        }

        if(obj instanceof String){
            return name.equals(obj);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
