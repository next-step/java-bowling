package qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {

    private List<DeleteHistory> list = new ArrayList<>();

    public void addQuestionDeleteHistory(long questionId, User writer) {
        list.add(new DeleteHistory(ContentType.QUESTION, questionId, writer, LocalDateTime.now()));
    }

    public void addAnswerDeleteHistory(Answer answer) {
        list.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
    }

    public List<DeleteHistory> getDeleteHistories() {
        return list;
    }
}
