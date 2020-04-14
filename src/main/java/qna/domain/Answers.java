package qna.domain;

import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = new ArrayList<>(answers);
    }

    public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            deleteHistories.add(answer.delete(loginUser));
        }
        return deleteHistories;
    }

    public boolean isDeletable(User loginUser) {
        return answers.stream()
                .allMatch(answer -> answer.isOwner(loginUser));
    }

}
