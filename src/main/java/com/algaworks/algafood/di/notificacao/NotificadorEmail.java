package com.algaworks.algafood.di.notificacao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;

@Profile("prod")
@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
@Component
public class NotificadorEmail implements Notificador {
	
	@Autowired
	Environment env;
	@Autowired
	NotificadorProperties properties;
	
	private String host;
	private Integer porta;

	public NotificadorEmail() {
		this.instanciaParametros();
		
		System.out.println("NotificadorEmail REAL");
		
		System.out.println("Host: " + host);
		System.out.println("Porta: " + porta);
	}

	@Override
	public void notificar(Cliente cliente, String mensagem) {
		System.out.println("Host: " + properties.getHostServidor());
		System.out.println("Porta: " + properties.getPortServidor());
		
		String hostServidor = env.getProperty("notificador.email.host-servidor");
		String portServidor = env.getProperty("notificador.email.port-servidor");
		System.out.println("Host: " + hostServidor);
		System.out.println("Port: " + portServidor);
		
		System.out.printf("Notificando %s através do e-mail %s: %s\n", 
				cliente.getNome(), cliente.getEmail(), mensagem);
		
		
	}
	
	public void instanciaParametros() {
		try {
			Properties props = new Properties();
			File file = new File("src/main/resources/application.properties");
			props.load(new FileInputStream(file));
			
			this.host = props.getProperty("notificador.email.host-servidor");
			this.porta = Integer.parseInt(props.getProperty("notificador.email.port-servidor"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void teste() {
		String hostServidor = env.getProperty("notificador.email.host-servidor");
		String portServidor = env.getProperty("notificador.email.port");
		System.out.println("Host: " + hostServidor);
		System.out.println("Port: " + portServidor);
	}
}