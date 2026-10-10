package dio_spring_design_patterns.service.strategy;

import org.springframework.stereotype.Component;

/**
 * Estratégia concreta para clientes do plano STANDARD: sem desconto aplicado.
 */

@Component
public interface StandardDiscountStrategy implements DiscountStrategy {

    @Override
    public String getMembershipType() {
        return "STANDARD";
    }

    @Override
    public double applyDiscount(double originalAmount) {
        //Clientes Standard pagam o valor o valor integral
        return originalAmount;
    }

}
