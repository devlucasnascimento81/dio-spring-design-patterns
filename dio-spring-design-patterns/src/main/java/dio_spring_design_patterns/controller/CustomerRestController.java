package dio_spring_design_patterns.controller;

import dio_spring_design_patterns.model.Customer;
import dio_spring_design_patterns.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller REST que expõe os endpoints para gerenciamento de clientes.
 * Atua como ponto de entrada da API, delegando as operações para a Facade (CustomerService).
 */
@RestController
@RequestMapping("customers")
public class CustomerRestController {
    @Autowired
    private CustomerService customerService;

    /**
     * Retorna a lista de todos os clientes cadastrados.
     */
    @GetMapping
    public ResponseEntity<Iterable<Customer>> findAll() {
        return ResponseEntity.ok(customerService.finAll());
    }

    /**
     * Busca um cliente específico através do seu identificador.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Cadastra um novo cliente e busca automaticamente o endereço via CEP.
     */
    @PostMapping
    public ResponseEntity<Customer> insert(@RequestBody Customer customer) {
        customerService.insert(customer);
        return ResponseEntity.ok(customer);
    }

    /**
     * Atualiza os dados de um cliente existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody Customer customer) {
        customerService.update(id, customer);
        return ResponseEntity.ok(customer);
    }

    /**
     * Remove um cliente pelo ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Simula o cálculo de desconto para o cliente usando o Padrão Strategy.
     * Exemplo de chamada: GET /customers/1/discount?amount=100.0
     */
    @GetMapping("/{id}/discount")
    public ResponseEntity<Double> calculateDiscount(@PathVariable Long id, @RequestParam double amount) {
        double finalAmount = customerService.calculateDiscount(id, amount);
        return ResponseEntity.ok(finalAmount);
    }
}
