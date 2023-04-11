import creditimpot.CalculService;
import creditimpot.Employee;
import creditimpot.Revenu;
import creditimpot.TypeRevenu;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CiceServiceTest {

    private final CalculService service = new CalculService();

    @Test
    public void compute() {
        final List<Employee> employees = new ArrayList<>();
        final Employee intern = new Employee();
        intern.setIntern(true);
        intern.setRevenus(new ArrayList<>());
        intern.getRevenus().add(new Revenu("500", TypeRevenu.BONUS));
        employees.add(intern);
        final Employee employee = new Employee();
        employee.setIntern(true);
        employee.setRevenus(new ArrayList<>());
        employee.getRevenus().add(new Revenu("2000", TypeRevenu.SALARY));
        employee.getRevenus().add(new Revenu("100", TypeRevenu.BONUS));
        employee.getRevenus().add(new Revenu("100", TypeRevenu.VOUCHERS));
        employees.add(employee);
        final Employee manager = new Employee();
        manager.setIntern(true);
        manager.setRevenus(new ArrayList<>());
        manager.getRevenus().add(new Revenu("2000", TypeRevenu.SALARY));
        manager.getRevenus().add(new Revenu("534", TypeRevenu.BONUS));
        employees.add(manager);
        assertEquals(new BigDecimal("308.04"), this.service.compute(employees));
    }
}
