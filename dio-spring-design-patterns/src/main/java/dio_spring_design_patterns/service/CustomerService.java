package dio_spring_design_patterns.service;

import dio_spring_design_patterns.model.Customer;

/**
 * Interface que define as operações de negócio para clientes.
 * Segue o princípio da inversão de dependência (IoC/DIP).
 */

public interface CustomerService {

    /**
     * Retorna todos os clientes cadastrados.
     */
    Iterable<Customer> findAll();

    /**
     * Busca um cliente pelo seu identificador único.
     */
    Customer findById(Long id);

    /**
     * Cadastra um novo cliente, consultando o CEP no ViaCEP e persistindo os dados.
     */
    void insert(Customer customer);

    /**
     * Atualiza os dados de um cliente existente.
     */
    void update(Long id, Customer customer);

    /**
     * Remove um cliente pelo ID.
     */
    void delete(Long id);

    /**
     * Simula o valor de uma compra para o cliente aplicando a regra de desconto (Strategy).
     *
     * @param customerId ID do cliente
     * @param amount valor original
     * @return valor com desconto aplicado
     */
    double calculateDiscount(Long customerId, double amount);
}
