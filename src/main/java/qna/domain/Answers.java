package qna.domain;

import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
public class Answers {
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private final List<Answer> value;

    public Answers() {
        value = new ArrayList<>();
    }

    public Answers(Answer... answers) {
        value = Arrays.asList(answers);
    }

    public void addAnswer(Question question, Answer answer) {
        answer.toQuestion(question);
        value.add(answer);
    }

    public boolean hasOtherOwnerDifferentFrom(User user) {
        return value.stream()
                .anyMatch(ans -> !ans.isOwner(user));
    }

    public List<DeleteHistory> deleteAll() {
        return value.stream()
                .peek(ans -> ans.setDeleted(true))
                .map(ans -> new DeleteHistory(ContentType.ANSWER, ans.getId(), ans.getWriter(), LocalDateTime.now()))
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }
}
