package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PersonTest {

    @Test
    void createPerson() {
        // Given
        String givenName = "PHS";
        Person person = Person.from(givenName);

        assertThat(person.name()).isEqualTo(givenName);
    }
}