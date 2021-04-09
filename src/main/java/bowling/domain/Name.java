package bowling.domain;

import bowling.exception.CustomException;
import bowling.exception.ErrorCode;

import java.util.regex.Pattern;

public class Name {

    private static final int VALID_LENGTH = 3;

    private String name;

    public Name(String name){
        if(!valid(name)){
            throw new CustomException(ErrorCode.INVALID_NAME);
        }
        this.name = name;
    }

    private boolean valid(String name){
        return Pattern.matches("[A-Za-z]+", name) && name.length()==VALID_LENGTH;
    }

    public String name(){
        return this.name;
    }
}
