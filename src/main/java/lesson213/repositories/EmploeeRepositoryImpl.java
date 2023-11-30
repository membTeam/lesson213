package lesson213.repositories;

import lesson213.exceptionAPI.ErrNotDataException;
import lesson213.models.Emploee;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class EmploeeRepositoryImpl implements  EmploeeRepository {

    private Map<String,Emploee> repo = new HashMap<>();
    private Stream<Emploee> streamEmploee = repo.values().stream();
    private ErrNotDataException errNotDataException = new ErrNotDataException("Нет данных");


    private static final Long MINNUMINN = 613450000001L;
    private Long getINN() {
        if (repo.values().size() == 0) {
            return MINNUMINN;
        }

        //repo.values().stream()
        var max = streamEmploee
                .mapToLong(emploee -> emploee.getDataINN())
                .max().orElseThrow();

        if (max < 613450000001L) {
            throw new ErrNotDataException("ИНН не верный формат");
        }

        return  (max / 1000 + 1) * 1000 + 1;
    }

    // ---------------------------------------------------

    public Map<String,Emploee> getMap() {
        return repo;
    }

    @Override
    public Optional<List<Emploee>> listEmploeeForDepartment(int department) {
        return Optional.ofNullable(
                streamEmploee
                        .filter(emploee -> emploee.getDepartment()==department)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public boolean existEmploee(Long inn, String firstName, String lastName) {
        return repo.values().stream()
                .filter(emploee -> (emploee.getDataINN().equals(inn) &&
                        emploee.getFirstName().equals(firstName) &&
                        emploee.getLastName().equals(lastName)))
                .count() > 0;
    }

    @Override
    public boolean existEmploee(Emploee emploee) {
        if (emploee.getId() == null) {
            return false;
        } else {
            return existEmploee(emploee.getDataINN(),
                    emploee.getFirstName(),
                    emploee.getLastName());
        }
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
    public Optional<Emploee> save(Emploee emploee) {

        if (emploee.getId() == null) {
            if (emploee.getDepartment() == null ||
                    emploee.getSalary() == null ||
                    emploee.getFirstName().isBlank() ||
                    emploee.getLastName().isBlank()) {

                throw new ErrNotDataException("Проверьте заполнение полей");
            }

            emploee.setId(UUID.randomUUID().toString());
            if (emploee.getDataINN() == null) {
                emploee.setDataINN(getINN());
            }

            repo.put(emploee.getId(), emploee);

        } else if (existEmploee(emploee)) {
            repo.put(emploee.getId(), emploee);
        } else {
            return Optional.empty();
        }

        return Optional.ofNullable(emploee);
    }

    @Override
    public Optional<Emploee> findByINN(Long valINN) {
        var item = streamEmploee
                .filter(emploee -> emploee.getDataINN().equals(valINN))
                .collect(Collectors.toList());

        if (item.size() > 0) {
            return Optional.ofNullable(item.get(0));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Emploee> findById(String id) {
        var item = streamEmploee
                .filter(emploee -> emploee.getId().equals(id)).collect(Collectors.toList());

        return item.size() > 0 ? Optional.ofNullable(item.get(0)) : Optional.empty();
    }

    @Override
    public int maxSalary() {
        return streamEmploee
                .mapToInt(emploee -> emploee.getSalary())
                .max().orElseThrow(()-> errNotDataException);
    }

    @Override
    public int maxSalary(int department) {

        return streamEmploee
                .filter(emploee -> emploee.getDepartment() == department)
                .mapToInt(emploee -> emploee.getSalary())
                .max().orElseThrow(()-> errNotDataException);
    }

    @Override
    public int minSalary() {
        return streamEmploee
                .mapToInt(emploee -> emploee.getSalary())
                .min().orElseThrow(()-> errNotDataException);
    }
    @Override
    public int minSalary(int department) {
        return streamEmploee
                .filter(emploee -> emploee.getDepartment() == department)
                .mapToInt(emploee -> emploee.getSalary())
                .min().orElseThrow(()-> errNotDataException);
    }

    @Override
    public int sumSalary() {
        return streamEmploee
                .mapToInt(emploee -> emploee.getSalary())
                .sum();
    }

    @Override
    public int sumSalary(int department) {
        return streamEmploee
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
