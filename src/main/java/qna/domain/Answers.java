package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Embeddable
public class Answers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private final List<Answer> answers = new ArrayList<>();

    public Stream<DeleteHistory> delete2(User loginUser, ContentType contentType, LocalDateTime createTime) throws CannotDeleteException {
        validateDeleteCondition2(loginUser);
        return answers.stream()
                .map(answer -> answer.delete(contentType, createTime));
    }

    private void validateDeleteCondition2(User loginUser) throws CannotDeleteException {
        boolean isAllWrittenByLoginUser = answers.stream()
                .allMatch(answer -> answer.isOwner(loginUser));
        if (!isAllWrittenByLoginUser) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public Stream<DeleteHistory> delete(ContentType contentType, LocalDateTime createTime) {
        return answers.stream()
                .map(answer -> answer.delete(contentType, createTime));
    }
}
