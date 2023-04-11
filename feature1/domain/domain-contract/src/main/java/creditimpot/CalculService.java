package creditimpot;

import java.math.BigDecimal;
import java.util.List;

public class CalculService {

    private static final BigDecimal TAUX = new BigDecimal("2.5");

    private static final BigDecimal MIN_WAGE = new BigDecimal("1014");

    private final static BigDecimal TAUX_BASIS = new BigDecimal("0.06");

    public BigDecimal compute(final List<Employee> employees) {
        if(employees == null || employees.isEmpty())
            return BigDecimal.ZERO;

        BigDecimal basis = employees.stream()
                .filter(e -> e.getIntern()
                        && e.calculateIncome().compareTo(TAUX.multiply(MIN_WAGE)) < 0)
                .map(Employee::calculateIncome)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return basis.multiply(TAUX_BASIS).stripTrailingZeros();
    }
}
