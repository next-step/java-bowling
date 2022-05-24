package qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

@Embeddable
public class Answers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();

    public List<Answer> get() {
        return answers;
    }

    public Answers() {}

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public boolean hasDifferentOwner(User writer) {
        return answers.stream()
            .anyMatch(answer -> !answer.isOwner(writer));
    }

    public List<DeleteHistory> deleteAllByUser(final User user) throws CannotDeleteException {
        if (hasDifferentOwner(user)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }

        return answers.stream()
            .map(answer -> answer.deleteByUser(user))
            .collect(Collectors.toList());
    }

    public int size() {
        return answers.size();
    }
}
