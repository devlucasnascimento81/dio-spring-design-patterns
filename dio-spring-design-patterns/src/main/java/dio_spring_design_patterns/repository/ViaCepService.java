package dio_spring_design_patterns.repository;

import dio_spring_design_patterns.model.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepService {

    /**
     * Consulta os dados de endereço a partir de um CEP informado.
     *
     * @param cep CEP com 8 dígitos
     * @return Objeto Address preenchido com os dados retornados
     */
    @GetMapping("/{cep}/json/")
    Address findBycep(@PathVariable("cep") String cep);
}
