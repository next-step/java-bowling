package qna.domain;

import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Answers {

    private List<Answer> answers = new ArrayList<>();

    public Answers(Answer answer) {
        answers.add(answer);
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void checkExistentAnswers(User loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.checkDeleteAuth(loginUser);
        }
    }

    public void deleteAll(DeleteHistories histories) {
        answers.forEach(answer -> answer.delete(histories));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Answers answers1 = (Answers) o;
        return Objects.equals(answers, answers1.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answers);
    }

    @Override
    public String toString() {
        return "Answers{" +
                "answers=" + answers +
                '}';
    }

}
