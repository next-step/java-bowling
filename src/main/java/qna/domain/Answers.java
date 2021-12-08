package qna.domain;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
public class Answers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private final List<Answer> answers = new ArrayList<>();

    protected Answers() {
    }

    public Answers(List<Answer> answers) {
        checkNotEmpty(answers);
        this.answers.addAll(answers);
    }

    private void checkNotEmpty(List<Answer> answers) {
        if (answers == null || answers.isEmpty()) {
            throw new IllegalArgumentException("하나 이상의 답변이 필요합니다.");
        }
    }

    public void add(Answer answer) {
        checkNotNull(answer);
        answers.add(answer);
    }

    private void checkNotNull(Answer answer) {
        if (answer == null) {
            throw new IllegalArgumentException("필수 값이 없습니다.");
        }
    }

    public List<DeleteHistory> delete(User user) {
        return answers.stream()
                .map(answer -> answer.delete(user))
                .collect(Collectors.toList());
    }
}
