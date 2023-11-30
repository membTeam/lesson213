package lesson213.web;

import lesson213.models.Emploee;
import lesson213.exceptionAPI.ErrNotDataException;
import lesson213.repositories.EmploeeRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class DepartmentControllerServImpl implements DepartmentControllerServ {

    private final EmploeeRepositoryImpl repo;

    public DepartmentControllerServImpl(EmploeeRepositoryImpl repo) {
        this.repo = repo;
    }

    @Override
    public List<Emploee> listEmploeeForDepartment(Integer department) {
        return  repo.findAll().orElseThrow()
                .stream().filter(emploee -> emploee.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public int sumSalaryForDepartment(Integer department) {
        return repo.sumSalary(department);
    }

    @Override
    public int maxSalaryForDepartment(Integer department) {
        return repo.maxSalary(department);
    }

    @Override
    public int minSalaryForDepartment(Integer department) {
        return repo.minSalary(department);
    }

    @Override
    public Map<Integer, List<Emploee>> listEmploeeGr() {
        return repo.findAll().orElseThrow(()->{throw new ErrNotDataException("Нет данных в репозитории");})
                .stream()
                .collect(Collectors.groupingBy(emploee -> emploee.getDepartment()));
    }
}
