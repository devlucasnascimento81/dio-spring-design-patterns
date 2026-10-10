package dio_spring_design_patterns.service.strategy;


import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Contexto do padrão Strategy.
 * O Spring injeta automaticamente todas as implementações de DiscountStrategy
 * registradas como Beans (@Component) em um Map indexado pelo tipo de plano.
 */
@Component
public class DiscountContext {

    private final Map<String, DiscountStrategy> strategies;

    public DiscountContext(List<DiscountStrategy> strategyList) {
        //Mapeia cada estratégia pela chave retornada por getMembershipType()
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(
                        strategy -> strategy.getMembershipType().toUpperCase(),
                        Function.identity()
                ));
    }
    /**
     * Aplica o desconto de acordo com o plano do cliente.
     * Caso o plano não seja encontrado, aplica a regra padrão (STANDARD).
     *
     * @param membershipType tipo de plano do cliente
     * @param amount valor original
     * @return valor com desconto aplicado
     */

    public double calculate(String membershipType, double amount) {
        String key = Optional.ofNullable(membershipType)
                .map(String::toUpperCase)
                .orElse("STANDARD");
        DiscountStrategy strategy = strategies.getOrDefault(key, strategies.get("STANDARD"));
        return strategy.applyDiscount(amount);
    }

}
