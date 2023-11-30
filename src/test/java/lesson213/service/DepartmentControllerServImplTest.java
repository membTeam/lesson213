package lesson213.service;

import lesson213.models.Emploee;
import lesson213.repositories.EmploeeRepositoryImpl;
import lesson213.web.DepartmentControllerServImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class DepartmentControllerServImplTest {

    private List<Emploee> lsEmploee = ParamsForTesting.lsEmploee;
    private int department = ParamsForTesting.department;

    @Mock
    private EmploeeRepositoryImpl emploeeRepo;
    @InjectMocks
    private DepartmentControllerServImpl contrServ;

    @BeforeEach
    private void setUp() {
        initMocks(this);
    }

    @Test
    public void getSize() {
        var size = 3;
        when(emploeeRepo.size()).thenReturn(size);

        var sizeRes = contrServ.size();

        assertTrue(sizeRes > 0);
        assertEquals(size, sizeRes);
    }

    @Test
    public void listEmploeeForDepartment() {

        var lsMock = Optional.ofNullable(lsEmploee.stream()
                    .filter(emploee -> emploee.getDepartment() == department)
                    .collect(Collectors.toList()));
        var size = lsMock.orElseThrow().size();

        when(emploeeRepo.listEmploeeForDepartment(department))
                .thenReturn(lsMock);

        var resServ = contrServ.listEmploeeForDepartment(department);

        assertEquals(size, resServ.size());
    }

    @Test
    public void sumSalaryForDepartment() {
        var sumSalary = 90000;
        when(emploeeRepo.sumSalary(department)).thenReturn(sumSalary);

        var resServ = contrServ.sumSalaryForDepartment(department);

        assertEquals(sumSalary, resServ);
    }

    @Test
    public void maxSalaryForDepartment() {
        var maxSalary = 65000;
        when(emploeeRepo.maxSalary(department)).thenReturn(maxSalary);

        var resServ = contrServ.maxSalaryForDepartment(department);

        assertEquals(maxSalary, resServ);
    }

    @Test
    public void minSalaryForDepartment() {
        var minSalary = 50000;
        when(emploeeRepo.minSalary(department)).thenReturn(minSalary);

        var resServ = contrServ.minSalaryForDepartment(department);

        assertEquals(minSalary, resServ);
    }

    @Test
    public void listEmploeeGr() {

        when(emploeeRepo.findAll()).thenReturn(Optional.ofNullable(lsEmploee));
        var resMap = lsEmploee.stream()
                .collect(Collectors.groupingBy(emploee -> emploee.getDepartment()));

        var resServ = contrServ.listEmploeeGr();

        assertEquals(resMap, resServ);
    }

}
