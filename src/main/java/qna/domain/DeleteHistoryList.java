package qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistoryList {

    private final List<DeleteHistory> deleteHistoryList;

    public DeleteHistoryList() {
        deleteHistoryList = new ArrayList<>();
    }

    public DeleteHistoryList(List<DeleteHistory> deleteHistoryList) {
        this.deleteHistoryList = deleteHistoryList;
    }

    public void addQuestionDeleteHistory(Question question) {
        deleteHistoryList.add(new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()));
    }

    public void addAnswerListDeleteHistory(AnswerList answerList) {
        for (Answer answer : answerList.value()) {
            answer.setDeleted(true);
            deleteHistoryList.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        }
    }

    public List<DeleteHistory> value() {
        return Collections.unmodifiableList(deleteHistoryList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeleteHistoryList that = (DeleteHistoryList) o;

        return deleteHistoryList != null ? deleteHistoryList.equals(that.deleteHistoryList) : that.deleteHistoryList == null;
    }

    @Override
    public int hashCode() {
        return deleteHistoryList != null ? deleteHistoryList.hashCode() : 0;
    }
}
