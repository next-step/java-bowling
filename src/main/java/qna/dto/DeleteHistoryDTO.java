package qna.dto;

import qna.domain.User;

import java.time.LocalDateTime;

public class DeleteHistoryDTO {
    private final long questionId;
    private final User writer;
    private final LocalDateTime dateTime;

    public DeleteHistoryDTO(long questionId, User writer, LocalDateTime dateTime) {
        this.questionId = questionId;
        this.writer = writer;
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public long getQuestionId() {
        return questionId;
    }

    public User getWriter() {
        return writer;
    }
}
