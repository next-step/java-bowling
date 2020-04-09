package qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {

    private final List<DeleteHistory> deleteHistories;

    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    public DeleteHistories(Question question) {
        this.deleteHistories = settingDeleteHistories(question);
    }

    public List<DeleteHistory> getDeleteHistories() {
        return new ArrayList<>(this.deleteHistories);
    }

    private List<DeleteHistory> settingDeleteHistories(Question question) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(DeleteHistory.newQuestion(question.getId(), question.getWriter()));
        for (Answer answer : question.getAnswers()) {
            deleteHistories.add(DeleteHistory.newAnswer(answer.getId(), answer.getWriter()));
        }
        return deleteHistories;
    }
}
