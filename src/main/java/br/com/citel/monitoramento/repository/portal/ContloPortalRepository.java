package br.com.citel.monitoramento.repository.portal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.citel.monitoramento.entity.CONTLO;
import br.com.citel.monitoramento.entity.CONTLOPK;

/**
 * Classe de repositório usando o SpringFramework para facilidades de CRUD.
 * 
 * @author lucas
 * 
 */
@Repository
public interface ContloPortalRepository extends JpaRepository<CONTLO, CONTLOPK> {
	/**
	 * Buscando os CONTLO por LOG_C_G_C_
	 * 
	 * @param cnpj
	 * @return
	 */
	@Query("select c from CONTLO c where c.LOG_C_G_C_ = ?1 ")
	List<CONTLO> findByCNPJ(String cnpj);
}
