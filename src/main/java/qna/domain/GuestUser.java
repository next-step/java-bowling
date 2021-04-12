package qna.domain;

public class GuestUser extends User{
/*
  private static class GuestUser extends User {
    @Override
    public boolean isGuestUser() {
      return true;
    }
  }
*/

  @Override
  public boolean isGuestUser() {
    return true;
  }
}
