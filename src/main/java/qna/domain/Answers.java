package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
public class Answers implements Iterable<Answer> {
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

    public List<DeleteHistory> deleteAll(User loginUser) throws CannotDeleteException {
        return value.stream()
                .map(answer -> answer.delete(loginUser))
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }
    public int size() {
        return value.size();
    }

    @Override
    public Iterator<Answer> iterator() {
        return value.iterator();
    }
}
