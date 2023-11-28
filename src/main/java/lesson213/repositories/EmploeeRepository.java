package lesson213.repositories;

import lesson213.Emploee;

import java.util.List;
import java.util.Optional;

public interface EmploeeRepository {

    void clearAll();

    Optional<Emploee> save(Emploee emploee);
    Optional<Integer> maxSalary();
    Optional<Integer> minSalary();
    Optional<Integer> sumSalary();
    Optional<List<Emploee>> findAll();

    Optional<Emploee> findById(String id);
    Optional<Integer> maxSalary(int department);
    Optional<Integer> sumSalary(int department);
    Optional<List<Emploee>> findAll(int department);
    Optional<Integer> MinSalary(int department);

}
