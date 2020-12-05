package qna.domain;

public interface DeleteHistoryRecordable {

    ContentType getContentType();

    Long getId();

    User getWriter();
}
