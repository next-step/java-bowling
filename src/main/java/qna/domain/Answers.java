package qna.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Answers {

    private List<Answer> answers;

    public Answers() {
        new ArrayList<Answer>();
    }
    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public boolean isEmpty() {
        return answers == null || answers.isEmpty() ? true : false;
    }

    public void add(Answer answer) {
        if (!answers.isEmpty()) {
            answers.add(answer);
        }
    }

    public boolean haveSameWriter(User loginUser) {
        if (!answers.isEmpty()) {
            return answers.stream()
                    .allMatch(answer -> answer.getWriter() == loginUser);
        }
        return true;
    }


    public void deleted(boolean bool) {
        if (!answers.isEmpty()) {
            answers.forEach(answer -> answer.deleted(true));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answers answers1 = (Answers) o;
        return Objects.equals(answers, answers1.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answers);
    }
}
