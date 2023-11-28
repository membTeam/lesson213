package lesson213.repositories;

import lesson213.Emploee;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class EmploeeRepositoryImpl implements  EmploeeRepository {
    private static  int numINN = 50000;
    private static String getINN() {
        return  "6134" + numINN++ + "001";
    }
    private Map<String,Emploee> repo = new HashMap<>();

    // ---------------------------------------------------

    @Override
    public void clearAll() {
        repo.clear();
    }

    @Override
    public Optional<Emploee> save (Emploee emploee) {

        if (emploee.getId() == null) {
            if (emploee.getDepartment() == null ||
                    emploee.getSalary() == null ||
                    emploee.getFirstName() == null ||
                    emploee.getLastName() == null) {
                return Optional.empty();
            }

            emploee.setId(UUID.randomUUID().toString());
            emploee.setINN(getINN());

            repo.put(emploee.getId(), emploee);
        } else {
            repo.put(emploee.getId(), emploee);
        }

        return Optional.ofNullable(emploee);
    }

    @Override
    public Optional<Emploee> findById(String id) {
        return Optional.ofNullable(repo.get(id));
    }

    @Override
    public Optional<Integer> maxSalary() {
        return Optional.empty();
    }

    @Override
    public Optional<Integer> minSalary() {
        return Optional.empty();
    }

    @Override
    public Optional<Integer> sumSalary() {
        return Optional.ofNullable(repo.values().stream()
                        .mapToInt(emploee -> emploee.getSalary())
                        .sum());
    }

    @Override
    public Optional<List<Emploee>> findAll() {
        return Optional.ofNullable(repo.values().stream().collect(Collectors.toList()));
    }

    @Override
    public Optional<Integer> maxSalary(int department) {

        return Optional.ofNullable(repo.values().stream()
                    .filter(emploee -> emploee.getDepartment() == department)
                    .mapToInt(emploee -> emploee.getSalary())
                    .max().orElseThrow());
    }

    @Override
    public Optional<Integer> sumSalary(int department) {
        return Optional.ofNullable(repo.values().stream()
                .filter(emploee -> emploee.getDepartment() == department)
                .mapToInt(emploee -> emploee.getSalary())
                .sum());
    }

    @Override
    public Optional<List<Emploee>> findAll(int department) {
        return Optional.ofNullable(repo.values()
                .stream()
                .filter(emploee -> emploee.getDepartment() == department)
                .collect(Collectors.toList()));
    }

    @Override
    public Optional<Integer> MinSalary(int department) {
        return Optional.ofNullable(repo.values().stream()
                .filter(emploee -> emploee.getDepartment() == department)
                .mapToInt(emploee -> emploee.getSalary())
                .min().orElseThrow());
    }

}
