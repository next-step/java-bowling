package qna.dto;

import qna.domain.User;

import java.time.LocalDateTime;

public class DeleteHistoryDTO {
    private final long questionId;
    private final User writer;

    public DeleteHistoryDTO(long questionId, User writer) {
        this.questionId = questionId;
        this.writer = writer;
    }

    public long getQuestionId() {
        return questionId;
    }

    public User getWriter() {
        return writer;
    }
}
