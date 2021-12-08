package qna.domain;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Embeddable
public class Answers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private final List<Answer> answers = new ArrayList<>();

    protected Answers() {
    }

    public void add(Answer answer) {
        checkCompatible(answer);
        answers.add(answer);
    }

    private void checkCompatible(Answer other) {
        if (other == null) {
            throw new IllegalArgumentException("필수 값이 없습니다.");
        }

        answers.stream()
                .filter(answer -> !answer.questionEquals(other))
                .findAny()
                .ifPresent(answer -> {
                    throw new NotCompatibleAnswerException(answer.getQuestion());
                });
    }

    public List<DeleteHistory> delete(User user) {
        return answers.stream()
                .map(answer -> answer.delete(user))
                .collect(toList());
    }
}
