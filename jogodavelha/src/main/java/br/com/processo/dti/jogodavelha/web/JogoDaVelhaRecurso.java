package br.com.processo.dti.jogodavelha.web;

import br.com.processo.dti.jogodavelha.servico.JogoDaVelhaServico;
import br.com.processo.dti.jogodavelha.servico.dto.NovaPartidaDTO;
import br.com.processo.dti.jogodavelha.servico.dto.PartidaDTO;
import br.com.processo.dti.jogodavelha.util.CustomMessageType;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * RECURSO responsável pelos end-points do jogo da velha
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class JogoDaVelhaRecurso {

    private final Logger log = LoggerFactory.getLogger(JogoDaVelhaRecurso.class);

    @Autowired
    private JogoDaVelhaServico jogoDaVelhaServico;

    /**
     * POST para criar uma nova partida de jogo da velha
     *
     * @return ResponseEntity com Status e DTO contendo o id do jogo e quem começa
     */
    @PostMapping("/game")
    public ResponseEntity<NovaPartidaDTO> novoJogo() throws URISyntaxException, IOException {
        log.info("Requisição para iniciar um novo jogo!");
        NovaPartidaDTO novaPartidaDTO = this.jogoDaVelhaServico.novoJogo();
        HttpHeaders headers = new HttpHeaders();
        headers.add("app-alert", "Partida criada com sucesso.");

        return ResponseEntity.created(new URI("/api/game/" + novaPartidaDTO.getId()))
                .headers(headers)
                .body(novaPartidaDTO);
    }

    /**
     * POST para inserir um novo movimento na partida de jogo da velha
     *
     * @return HTTP 200 caso sucesso; Msg de erro caso não
     */
    @PostMapping("/game/{id}/movement")
    public ResponseEntity<?> novoMovimento(@PathVariable String id, @RequestBody PartidaDTO partidaDTO) throws IOException {
        log.info("Requisição para um novo movimento!");
        if (this.jogoDaVelhaServico.checarPartidaInexistente(id)) {
            return new ResponseEntity(new CustomMessageType("Partida não encontrada"), HttpStatus.BAD_REQUEST);
        }
        if (this.jogoDaVelhaServico.checarPartidaConcluida(id)) {
            return new ResponseEntity(new CustomMessageType("Partida já encerrada"), HttpStatus.BAD_REQUEST);
        }
        if (!this.jogoDaVelhaServico.checarTurnoJogador(partidaDTO.getId(), partidaDTO.getPlayer().equalsIgnoreCase("O") ? 1L : 2L)) {
            return new ResponseEntity(new CustomMessageType("Não é turno do jogador"), HttpStatus.BAD_REQUEST);
        }
        CustomMessageType customMessageType = this.jogoDaVelhaServico.efetuarJogada(partidaDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.add("app-alert", "Movimento executado com sucesso.");
        return ResponseEntity.ok()
                .headers(headers)
                .body(customMessageType == null ? "" : customMessageType);
    }

    /**
     * GET para exibir no console o status atual do tabuleiro
     *
     * @param id da partida
     * @throws IOException exception IO
     */
    @GetMapping("/game/show-atual/{id}")
    public void exibirTabuleiroConsole(@PathVariable String id) throws IOException {
        this.jogoDaVelhaServico.exibirTabuleiroConsole(id);
    }

}
