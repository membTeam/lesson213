package lesson213.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lesson213.models.Emploee;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentControllerServImpl servController;

    @GetMapping("/{id}/employees")
    public Iterable<Emploee> listEmploeeForDepartment(@PathVariable("id") Integer id ) {
        return servController.listEmploeeForDepartment(id);
    }

    @GetMapping("/{id}/salary/sum")
    public Integer sumSalaryForDepartment(@PathVariable("id") Integer id) {
        return servController.sumSalaryForDepartment(id);
    }

    @GetMapping("/{id}/salary/max")
    public Integer maxSalaryForDepartment(@PathVariable("id") Integer id) {
        return servController.maxSalaryForDepartment(id);
    }

    @GetMapping("/{id}/salary/min")
    public Integer minSalaryForDepartment(@PathVariable("id") Integer id) {
        return servController.minSalaryForDepartment(id);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Emploee>> allEmploee() {
        return servController.listEmploeeGr();
    }

}
