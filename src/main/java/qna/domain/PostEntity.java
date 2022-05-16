package qna.domain;

public interface PostEntity {
  Long getId();
  User getWriter();
  boolean isOwner(User loginUser);
  ContentType getContentType();
}
