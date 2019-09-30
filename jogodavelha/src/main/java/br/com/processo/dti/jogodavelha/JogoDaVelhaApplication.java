package br.com.processo.dti.jogodavelha;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class JogoDaVelhaApplication {

    private static final Logger log = LoggerFactory.getLogger(JogoDaVelhaApplication.class);

    /**
     * Método principal, usado para iniciar a aplicação.
     *
     * @param args argumentos de linha de comando.
     */
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(JogoDaVelhaApplication.class);
        Environment env = app.run(args).getEnvironment();
        String protocol = "http";
        String hostAddress = "localhost";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.warn("O nome do host pode estar errado!", e);
        }
        infoAppStart(env, protocol, hostAddress);
    }

    /**
     * Método para logar as informações da aplicação.
     *
     * @param env         Environment
     * @param protocol    protocol
     * @param hostAddress hostAddress
     */
    protected static void infoAppStart(Environment env, String protocol, String hostAddress) {
        log.info("\n----------------------------------------------------------\n\t" +
                        "Aplicação '{}' está rodando! URL de acesso:\n\t" +
                        "Local: \t\t{}://localhost:{}\n\t" +
                        "Externo: \t{}://{}:{}\n\t" +
                        "Perfil: \t{}\n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                protocol,
                env.getProperty("server.port"),
                protocol,
                hostAddress,
                env.getProperty("server.port"),
                env.getActiveProfiles());
    }
}
