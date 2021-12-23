package qna.domain;

import java.util.List;

public class Answers {
    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public boolean isNotOwner(User loginUser) {
        return answers.stream().anyMatch(answer -> !answer.isOwner(loginUser));
    }

    public void addAnswersDeleteHistories(List<DeleteHistory> deleteHistories, Question question) {
        for (Answer answer : answers) {
            answer.setDeleted(true);
            deleteHistories.add(DeleteHistory.createAnswerDeleteHistory(answer));
        }
    }
}
