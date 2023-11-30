package lesson213.repositories;

import lesson213.models.Emploee;

import java.util.List;
import java.util.Optional;

public interface EmploeeRepository {

    Optional<Emploee> save(Emploee emploee);
    Optional<List<Emploee>> findAll();
    Optional<List<Emploee>> listEmploeeForDepartment(int department);
    Optional<Emploee> findByINN(Long valINN);
    Optional<Emploee> findById(String id);
    Optional<List<Emploee>> findAll(int department);

    void clearAll();
    int size();

    int minSalary();
    int sumSalary();
    boolean existEmploee(Long inn, String firstName, String lastName);
    boolean existEmploee(Emploee emploee);

    int maxSalary(int department);
    int maxSalary();
    int sumSalary(int department);
    int minSalary(int department);

}
