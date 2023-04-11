package creditimpot;

import java.math.BigDecimal;

public class Revenu {
    private BigDecimal montant;
    private TypeRevenu type;

    public Revenu(final String montant, final TypeRevenu type) {
        this.montant = new BigDecimal(montant);
        this.type = type;
    }

    public BigDecimal getMontant() {
        return this.montant;
    }

    public void setMontant(final BigDecimal montant) {
        this.montant = montant;
    }

    public TypeRevenu getType() {
        return this.type;
    }

    public void setType(final TypeRevenu type) {
        this.type = type;
    }
}
