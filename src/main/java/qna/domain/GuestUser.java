package qna.domain;

public class GuestUser extends User{

  @Override
  public boolean isGuestUser() {
    return true;
  }
}
