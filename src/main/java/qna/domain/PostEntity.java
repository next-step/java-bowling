package qna.domain;

public interface PostEntity {
  Long getId();
  User getWriter();
  ContentType getContentType();
}
