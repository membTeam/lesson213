package lesson213.repositories;

import lesson213.models.Emploee;

import java.util.List;
import java.util.Optional;

public interface EmploeeRepository {

    void clearAll();

    Optional<Emploee> save(Emploee emploee);
    int minSalary();
    int sumSalary();
    Optional<List<Emploee>> findAll();


    int size();
    boolean existEmploee(Long inn, String firstName, String lastName);
    boolean existEmploee(Emploee emploee);

    Optional<Emploee> findById(String id);
    int maxSalary(int department);
    int maxSalary();
    int sumSalary(int department);
    Optional<List<Emploee>> findAll(int department);
    int minSalary(int department);

}
