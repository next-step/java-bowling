package qna.domain;

import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {

    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
        this.loginUser = loginUser;
    }

    public static Answers of(List<Answer> answers, User loginUser) {
        return new Answers(answers,loginUser);
    }

    public void canDelete() throws CannotDeleteException {
        for (Answer answer : this.answers) {
            answer.canDelete(loginUser);
        }
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public List<DeleteHistory> deleteAnswer(User loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer: answers) {
            deleteHistories.add(answer.deleteAnswer(loginUser));
        }
        return deleteHistories;
    }
}
