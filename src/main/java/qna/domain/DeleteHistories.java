package qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {
    List<DeleteHistory> deleteHistories = new ArrayList<>();
    Question question;

    public DeleteHistories() {
    }

    public DeleteHistories(Question question) {
        this.question = question;
    }

    public void addDeleteHistoryForQuestion() {
        deleteHistories.add(
                new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(),
                                  LocalDateTime.now()));
    }

    public void addDeleteHistoryForAnswers() {
        List<Answer> answers = question.getAnswers();
        for (Answer answer : answers) {
            addDeleteHistoryForAnswer(answer);
        }
    }

    private void addDeleteHistoryForAnswer(Answer answer) {
        deleteHistories.add(
                new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
    }

    public List<DeleteHistory> getDeleteHistories() {
        return deleteHistories;
    }

}
