package qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Answers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private final List<Answer> answers = new ArrayList<>();

    public void delete(User loginUser) throws CannotDeleteException {
        List<Answer> deleteAnswers = answers.stream()
            .filter(answer -> answer.isOwner(loginUser))
            .map(answer -> answer.setDeleted(true))
            .collect(Collectors.toList());

        validDelete(deleteAnswers);
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    private void validDelete(List<Answer> deleteAnswers) throws CannotDeleteException {
        if (deleteAnswers.size() != answers.size()) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    public List<Answer> getAnswers() {
        return Collections.unmodifiableList(answers);
    }
}
