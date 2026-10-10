package dio_spring_design_patterns.service.strategy;

/**
 * Padrão Strategy: Interface que define o contrato comum para diferentes
 * estratégias de cálculo de desconto com base no plano do cliente.
 */

public interface DiscountStrategy {

    /**
     * Retorna o tipo de plano suportado por esta estratégia (ex: STANDARD, VIP, PREMIUM).
     */
    String getMembershipType();
    /**
     * Calcula o valor final com base no valor original informado.
     *
     * @param originalAmount valor original da compra/serviço
     * @return valor final após aplicação da regra de desconto
     */
    double applyDiscount(double originalAmount);

}
