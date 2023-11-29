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
    public boolean existEmploee(String inn, String firstName, String lastName) {
        return repo.values().stream()
                .filter(emploee -> (emploee.getDataINN().equals(inn) &&
                        emploee.getFirstName().equals(firstName) &&
                        emploee.getLastName().equals(lastName)))
                .count() > 0;
    }

    @Override
    public boolean existEmploee(Emploee emploee) {
        return existEmploee(emploee.getDataINN(), emploee.getFirstName(), emploee.getLastName());
    }

    @Override
    public int size() {
        return repo.size();
    }

    @Override
    public void clearAll() {
        repo.clear();
    }

    @Override
    public Optional<Emploee> save (Emploee emploee) {

        if (existEmploee(emploee)) {
            return Optional.empty();
        }

        if (emploee.getId() == null) {
            if (emploee.getDepartment() == null ||
                    emploee.getSalary() == null ||
                    emploee.getFirstName() == null ||
                    emploee.getLastName() == null) {
                return Optional.empty();
            }

            emploee.setId(UUID.randomUUID().toString());
            if (emploee.getDataINN() == null) {
                emploee.setDataINN(getINN());
            }

            repo.put(emploee.getId(), emploee);
        } else {
            repo.put(emploee.getId(), emploee);
        }

        return Optional.ofNullable(emploee);
    }

    @Override
    public Optional<Emploee> findById(String id) {
        var item = repo.values()
                .stream()
                .filter(emploee -> emploee.getId().equals(id)).collect(Collectors.toList());

        return item.size() > 0 ? Optional.of(item.get(0)) : Optional.empty();
    }

    @Override
    public int maxSalary() {
        return repo.values().stream()
                .mapToInt(emploee -> emploee.getSalary())
                .max().orElseThrow();
    }

    @Override
    public int maxSalary(int department) {

        return repo.values().stream()
                .filter(emploee -> emploee.getDepartment() == department)
                .mapToInt(emploee -> emploee.getSalary())
                .max().orElseThrow();
    }

    @Override
    public int minSalary() {
        return repo.values().stream()
                .mapToInt(emploee -> emploee.getSalary())
                .min().orElseThrow();
    }
    @Override
    public int minSalary(int department) {
        return repo.values().stream()
                .filter(emploee -> emploee.getDepartment() == department)
                .mapToInt(emploee -> emploee.getSalary())
                .min().orElseThrow();
    }

    @Override
    public int sumSalary() {
        return repo.values().stream()
                .mapToInt(emploee -> emploee.getSalary())
                .sum();
    }

    @Override
    public int sumSalary(int department) {
        return repo.values().stream()
                .filter(emploee -> emploee.getDepartment() == department)
                .mapToInt(emploee -> emploee.getSalary())
                .sum();
    }

    @Override
    public Optional<List<Emploee>> findAll() {
        return Optional.ofNullable(repo.values().stream().collect(Collectors.toList()));
    }

    @Override
    public Optional<List<Emploee>> findAll(int department) {
        return Optional.ofNullable(repo.values()
                .stream()
                .filter(emploee -> emploee.getDepartment() == department)
                .collect(Collectors.toList()));
    }

}
