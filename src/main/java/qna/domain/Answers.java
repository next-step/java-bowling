package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
public class Answers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private final List<Answer> answers = new ArrayList<>();

    public void add(Answer answer) {
        answers.add(answer);
    }

    protected void checkAuthorization(User loginUser) {
        if (isAuthorizationMatches(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    private boolean isAuthorizationMatches(User loginUser) {
        return answers.stream().anyMatch(answer -> !answer.isOwner(loginUser));
    }

    public List<DeleteHistory> deleteAll(User loginUser) {
        isAuthorizationMatches(loginUser);
        return answers.stream()
                .map(Answer::deleteAnswers)
                .collect(Collectors.toList());
    }

    public List<Answer> answers() {
        return Collections.unmodifiableList(answers);
    }
}
