package dio_spring_design_patterns.service.strategy;

import org.springframework.stereotype.Component;

/**
 * Estratégia concreta para clientes do plano VIP: concede 10% de desconto.
 */
@Component
public interface VipDiscountStrategy implements DiscountStrategy{

    @Override
    public String getMembershipType() {
        return "VIP";
    }

    @Override
    public double applyDiscount(double originalAmount) {
        // Concede 10% de desconto
        return originalAmount * 0.90;
    }

}
