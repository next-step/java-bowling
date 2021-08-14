package qna.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answers {
    @OrderBy("id asc")
    @Where(clause = "deleted = false")
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

    public List<DeleteHistory> delete(final User loginUser) {
        return answers.stream()
                .map(deleteBy(loginUser))
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    private Function<Answer, DeleteHistory> deleteBy(final User loginUser) {
        return answer -> answer.delete(loginUser);
    }

    public void add(final Answer answer) {
        answers.add(answer);
    }

    public int size() {
        return answers.size();
    }
}
