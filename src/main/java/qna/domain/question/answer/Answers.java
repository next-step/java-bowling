package qna.domain.question.answer;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;
import qna.domain.deleteHistory.DeleteHistory;
import qna.domain.user.User;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Embeddable
public class Answers {
    public static final String UNAUTHORIZED_ANSWER_MESSAGE = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> values = new ArrayList<>();

    public Answers() {
    }

    public Answers(List<Answer> answers) {
        this.values = answers;
    }

    public static Answers from(List<Answer> answers) {
        return new Answers(answers);
    }

    public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException(UNAUTHORIZED_ANSWER_MESSAGE);
        }

        return values.stream()
                .map(Answer::delete)
                .collect(Collectors.toList());
    }

    private boolean isOwner(User loginUser) {
        return values.stream().allMatch(answer -> answer.isOwner(loginUser));
    }

    public void add(Answer answer) {
        values.add(answer);
    }

    public List<Answer> values() {
        return values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answers answers = (Answers) o;
        return Objects.equals(values, answers.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }

    @Override
    public String toString() {
        return "Answers{" +
                "values=" + values +
                '}';
    }
}
