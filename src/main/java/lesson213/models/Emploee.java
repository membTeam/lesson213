package lesson213.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class Emploee {

    private String id;
    private Long dataINN;
    private String firstName;
    private String lastName;
    private Integer department;
    private Integer salary;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emploee emploee = (Emploee) o;
        return Objects.equals(dataINN, emploee.dataINN);
    }

}
