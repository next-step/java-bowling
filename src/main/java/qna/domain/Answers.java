package qna.domain;

import qna.CannotDeleteException;

import java.time.LocalDateTime;
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

    public void checkExistentAnswer(User loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.checkExistentAnswer(loginUser);
        }
    }

    public List<Answer> getAnswers() {
        return answers;
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
