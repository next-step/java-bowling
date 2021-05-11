package qna.domain;

import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private List<Answer> answers = new ArrayList<>();

    public Answers() {
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public void isPossibleToDelete(User loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.isPossibleToDelete(loginUser);
        }
    }

    public void allDelete(DeleteHistories deleteHistories) {
        for (Answer answer : answers) {
            deleteHistories.add(answer.delete());
        }
    }
}
