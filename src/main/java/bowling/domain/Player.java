package bowling.domain;

import bowling.error.NameInvalidLengthException;

public class Player {
  private static final int MAX_LENGTH = 3;

  private final String name;

  public Player(String name){
    checkValidatedNameLength(name);
    this.name = name;
  }

  public void checkValidatedNameLength(String name){
    if(name.isEmpty() || name.length() != MAX_LENGTH){
      throw new NameInvalidLengthException();
    }
  }

  public String name() {
    return name;
  }

}
