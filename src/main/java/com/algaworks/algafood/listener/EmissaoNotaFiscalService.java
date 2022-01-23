package com.algaworks.algafood.listener;

import com.algaworks.algafood.di.service.ClienteAtivadoEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class EmissaoNotaFiscalService {

	@EventListener
	@Order(2)
	public void clienteAtivadoListener(ClienteAtivadoEvent event) throws InterruptedException {
		System.out.println("Emitindo nota fiscal para cliente " + event.getCliente().getNome());
	}
}
	
