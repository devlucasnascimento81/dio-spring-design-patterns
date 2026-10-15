package dio_spring_design_patterns.service;

import dio_spring_design_patterns.model.Address;
import dio_spring_design_patterns.model.Customer;
import dio_spring_design_patterns.repository.AddressRepository;
import dio_spring_design_patterns.repository.CustomerRepository;
import dio_spring_design_patterns.service.strategy.DiscountContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementação do serviço de clientes.
 * Padrões aplicados:
 * - Singleton: o Spring gerencia esta classe como um Bean único (@Service).
 * - Facade: abstrai a complexidade entre repositórios, API externa (ViaCEP) e o cálculo de descontos.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Autowired
    private DiscountContext discountContext;

    @Override
    public Iterable<Customer> findAll() {
        // Retorna todos os clientes cadastrados no banco
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Long id) {
        // Busca o cliente pelo ID ou retorna null se não encontrar
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElse(null);
    }

    @Override
    public void insert(Customer customer) {
        //Persiste o cliente garantindo a integridade do endereço com o viacep
        saveCustomerWithCep(customer);
    }

    @Override
    public void update(Long id, Customer customer){
        //verifica se o cliente existe antes de atualizar
        Optional<Customer> customerBd = customerRepository.findById(id);
        if (customerBd.isPresent()) {
            customer.setId(id);
            saveCustomerWithCep(customer);
        }
    }

    @Override
    public void delete(Long id) {
        // remove o cliente pelo identificador
        customerRepository.deleteById(id);
    }

    @Override
    public double calculateDiscount(Long customerId, double amount) {
        // busca o cliente e delega o cálculo ao contexto do strategy com base no seu membershipType
        Customer customer = findById(customerId);
        if (customer != null) {
            return  discountContext.calculate(customer.getMembershipType(), amount);
        }
        //caso o cliente não exista, aplica a regra padrão sem desconto
        return amount;
    }

    /**
     * Método auxiliar privado que centraliza a lógica de busca/salvamento do endereço via CEP.
     * Caso o endereço já exista no banco local, reaproveita; senão, consulta no ViaCEP.
     */

    private void saveCustomerWithCep(Customer customer) {
        String cep = customer.getAddress().getCep();
        Address address = addressRepository.findById(cep).orElseGet(() -> {
            Address newAddress = viaCepService.findBycep(cep);
            addressRepository.save(newAddress);
            return newAddress;
        });

        customer.setAddress(address);
        customerRepository.save(customer);
    }
}
