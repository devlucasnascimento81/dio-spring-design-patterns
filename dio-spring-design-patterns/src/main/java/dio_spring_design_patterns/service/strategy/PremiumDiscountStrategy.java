package dio_spring_design_patterns.service.strategy;

import org.springframework.stereotype.Component;

/**
 * Estratégia concreta para clientes do plano PREMIUM: concede 20% de desconto.
 */
@Component
public class PremiumDiscountStrategy implements DiscountStrategy{

    @Override
    public String getMembershipType() {
        return "PREMIUM";
    }

    @Override
    public double applyDiscount(double originalAmount) {
        //Concede 20% de desconto
        return originalAmount * 0.80;
    }
}
