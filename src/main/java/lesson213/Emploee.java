package lesson213;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class Emploee {

    private String id;
    private String INN;
    private String firstName;
    private String lastName;
    private Integer department;
    private Integer salary;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emploee emploee = (Emploee) o;
        return Objects.equals(INN, emploee.INN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(INN);
    }
}
