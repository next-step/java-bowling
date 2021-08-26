package qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Answers {
    private final List<Answer> answers;

    public static Answers of(List<Answer> answers) {
        return new Answers(answers);
    }

    private Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public Answers() {
        answers = new ArrayList<>();
    }

    public boolean isOwner(User loginUser) {
        return answers.stream()
                .filter(answer -> answer.isOwner(loginUser))
                .findFirst()
                .isPresent();

    }

    public void setDeleted(boolean deleted) {
        for(Answer answer : answers){
            answer.setDeleted(deleted);
        }
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public List<DeleteHistory> getDeleted() {
        return answers.stream()
                .filter(answer -> answer.isDeleted())
                .map(answer -> new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()))
                .collect(Collectors.toList());
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
