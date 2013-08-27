package br.com.citel.monitoramento;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.citel.monitoramento.repository.autcom.ContloAutComRepository;
import br.com.citel.monitoramento.repository.autcom.ContmoAutComRepository;
import br.com.citel.monitoramento.repository.portal.ContloPortalRepository;
import br.com.citel.monitoramento.repository.portal.ContmoPortalRepository;

/**
 * Classe de responsável por fazer a sincronia do CONTMO e CONLO
 * 
 * @author lucas
 * 
 */
@Log4j
public class DatabaseSync {

	@Setter
	private String enviaDados;
	@Setter
	private Long empresaFisica;
	@Setter
	private String cnpjEmpresa;
	@Autowired
	private ContmoAutComRepository contmoAutComRepository;
	@Autowired
	private ContmoPortalRepository contmoPortalRepository;
	@Autowired
	private ContloAutComRepository contloAutComRepository;
	@Autowired
	private ContloPortalRepository contloPortalRepository;

	public void run() {
		try {
			if ("1".equals(enviaDados)) {
				processaCONTLO();
				log.info("CONTLO ENVIADO PARA O PORTAL");
				processaCONTMO();
				log.info("CONTMO ENVIADO PARA O PORTAL");
			} else {
				log.info("ENVIO DE DADOS DESLIGADO");
			}
		} catch (Throwable t) {
			log.error("ERRO AO PROCESSAR", t);
		}
	}

	private void processaCONTLO() {
		contloPortalRepository.deleteByCNPJ(cnpjEmpresa);
		contloPortalRepository.bulkSaveWithoutCheksExists(contloAutComRepository.findAll());
	}

	private void processaCONTMO() {
		contmoPortalRepository.deleteByEmpresaFiscaAndCNPJ(empresaFisica, cnpjEmpresa);
		contmoPortalRepository.bulkSaveWithoutCheksExists(contmoAutComRepository.findAll());
	}

}
