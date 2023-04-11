package creditimpot;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Employee {
    private List<Revenu> revenus;
    private Boolean intern;

    public List<Revenu> getRevenus() {
        return revenus;
    }

    public void setRevenus(List<Revenu> revenus) {
        this.revenus = revenus;
    }

    public Boolean getIntern() {
        return intern;
    }

    public void setIntern(Boolean intern) {
        this.intern = intern;
    }

    public BigDecimal calculateIncome() {
        return revenus.stream()
                .filter(r -> Arrays.asList(TypeRevenu.SALARY, TypeRevenu.BONUS, TypeRevenu.OVERTIME, TypeRevenu.VACATION_PAY).contains(r.getType()))
                .map(Revenu::getMontant)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
