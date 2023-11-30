package lesson213.web;



import java.util.List;
import java.util.Map;

import lesson213.models.Emploee;

public interface DepartmentControllerServ {

    List<Emploee> listEmploeeForDepartment(Integer department);
    int size();
    int sumSalaryForDepartment(Integer department);
    int maxSalaryForDepartment(Integer department);
    int minSalaryForDepartment(Integer department);
    Map<Integer, List<Emploee>> listEmploeeGr();

}
