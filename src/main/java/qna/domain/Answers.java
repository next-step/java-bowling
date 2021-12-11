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
import java.util.stream.Collectors;

@Embeddable
public class Answers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();

    public Answers() {
    }

    public List<Answer> answers() {
        return answers;
    }

    public List<DeleteHistory> delete(User loginUser) {
        return answers.stream()
                .peek(answer -> delete(answer, loginUser))
                .map(answer -> new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()))
                .collect(Collectors.toList());
    }

    private void delete(Answer answer, User loginUser) {
        try {
            answer.delete(loginUser);
        } catch (CannotDeleteException e) {
            e.printStackTrace();
        }
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public boolean isOther(User loginUser) {
        return answers.stream()
                .anyMatch(answer -> !answer.isOwner(loginUser));
    }
}
