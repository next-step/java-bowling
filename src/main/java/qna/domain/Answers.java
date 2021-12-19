package qna.domain;

import java.util.ArrayList;
import java.util.Arrays;
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

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Answers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private final List<Answer> answers = new ArrayList<>();

    public List<DeleteHistory> delete(User loginUser) {
        return answers.stream()
            .map(answer -> answer.delete(loginUser))
            .collect(Collectors.toList());
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    public void addAll(Answer... answers) {
        this.answers.addAll(Arrays.asList(answers));
    }

    public List<Answer> getAnswers() {
        return Collections.unmodifiableList(answers);
    }
}
