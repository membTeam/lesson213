package lesson213.service;

import lesson213.exceptionAPI.ErrNotDataException;
import lesson213.models.Emploee;
import lesson213.repositories.EmploeeRepositoryImpl;
import lesson213.web.DepartmentControllerServImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class DepartmentControllerServImplTest {

    @Mock
    private EmploeeRepositoryImpl emploeeRepo;

    @InjectMocks
    private DepartmentControllerServImpl contrServ;

    private int departmentForList = 1;
    private List<Emploee> lsEmploee = new ArrayList<>(
            List.of(
                    new Emploee("100", 613450000001L, "Николай", "Иванов", departmentForList, 50000),
                    new Emploee("101", 613450001001L, "Петр", "Иванов", departmentForList, 90000)
            )
    );

    @BeforeEach
    private void setUp() {
        initMocks(this);
    }

    @Test
    public void getSize() {
        var size = 3;
        when(emploeeRepo.size()).thenReturn(size);

        var sizeRes = contrServ.size();

        assertTrue( sizeRes > 0);
        assertEquals(size, sizeRes);
    }

    @Test
    public void listEmploeeForDepartment() {
        var department = 1;
        when(emploeeRepo.listEmploeeForDepartment(department))
                .thenReturn(Optional.ofNullable(lsEmploee));
        when(emploeeRepo.size()).thenReturn(2);

        var resServ = contrServ.listEmploeeForDepartment(department);

        assertEquals(emploeeRepo.size(), resServ.size());
    }

    @Test
    public void sumSalaryForDepartment() {
        var department = 1;
        var sumSalary = 90000;
        when(emploeeRepo.sumSalary(department)).thenReturn(sumSalary);

        var resServ = contrServ.sumSalaryForDepartment(department);

        assertEquals(sumSalary, resServ);
    }

    @Test
    public void maxSalaryForDepartment() {
        var department = 1;
        var maxSalary = 65000;
        when(emploeeRepo.maxSalary(department)).thenReturn(maxSalary);

        var resServ = contrServ.maxSalaryForDepartment(department);

        assertEquals(maxSalary, resServ);
    }

    @Test
    public void minSalaryForDepartment() {
        var department = 1;
        var minSalary = 50000;
        when(emploeeRepo.minSalary(department)).thenReturn(minSalary);

        var resServ = contrServ.minSalaryForDepartment(department);

        assertEquals(minSalary, resServ);
    }

    @Test
    public void listEmploeeGr() {

        when(emploeeRepo.findAll()).thenReturn(Optional.ofNullable(lsEmploee));
        var resMap = lsEmploee.stream().collect(Collectors.groupingBy(emploee -> emploee.getDepartment()));

        var resServ = contrServ.listEmploeeGr();

        assertEquals(resMap, resServ);
    }

}
