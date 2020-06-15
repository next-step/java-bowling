package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Embeddable
public class Answers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private final List<Answer> answers = new ArrayList<>();

    public void validateDeleteCondition(User loginUser) throws CannotDeleteException {
        boolean isAllWrittenByLoginUser = answers.stream()
                .allMatch(answer -> answer.isOwner(loginUser));
        if (!isAllWrittenByLoginUser) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public Stream<DeleteHistory> delete() {
        return answers.stream()
                .map(Answer::delete);
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
