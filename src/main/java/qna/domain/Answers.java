package qna.domain;

import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Embeddable
public class Answers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();

    private Answers(List<Answer> answers) {
        this.answers = Collections.unmodifiableList(answers);
    }

    public static Answers from(List<Answer> answers) {
        if (Objects.isNull(answers)) {
            throw new IllegalArgumentException("생성에 필요한 answers가 null이 전달되었습니다.");
        }
        return new Answers(answers);
    }

    protected Answers() {
    }

    public Answers add(Answer answer) {
        if (Objects.isNull(answer)) {
            throw new IllegalArgumentException("추가할 답변이 null이 전달되었습니다.");
        }
        List<Answer> newAnswers = new ArrayList<>(answers.size() + 1);
        newAnswers.addAll(answers);
        newAnswers.add(answer);
        return new Answers(newAnswers);
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public boolean isEmpty() {
        return answers.isEmpty();
    }

    public boolean deletable(User loginUser) {
        return answers.stream()
                .allMatch(answer -> answer.isOwner(loginUser));
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

    @Override
    public String toString() {
        return "Answers{" +
                "answers=" + answers +
                '}';
    }
}
