package br.com.processo.dti.jogodavelha.servico;

import br.com.processo.dti.jogodavelha.servico.dto.NovaPartidaDTO;
import br.com.processo.dti.jogodavelha.servico.dto.PartidaDTO;
import br.com.processo.dti.jogodavelha.util.CustomMessageType;

import javax.validation.Valid;
import java.io.IOException;

/**
 * Classe de serviço resposável pelo Jogo da Velha
 */
public interface JogoDaVelhaServico {

    /**
     * Método para criar um novo jogo.
     *
     * @return DTO com os dados da partida
     */
    NovaPartidaDTO novoJogo() throws IOException;

    /**
     * Método para checar se a partida existe.
     *
     * @param idPartida UUID da partida
     * @return true se não existir, false se existir.
     */
    boolean checarPartidaInexistente(String idPartida);

    /**
     * Método para checar se a partida já foi concluida
     * @param idPartida UUID da partida
     * @return true se sim, false se não
     */
    boolean checarPartidaConcluida(String idPartida);

    /**
     * Método para checar se é a vez do jogador.
     *
     * @param idPartida UUID da partida
     * @param idJogador id que representa o jogador (1 = O, 2 = X)
     * @return true se for a vez do jogador, false caso não seja.
     */
    boolean checarTurnoJogador(String idPartida, Long idJogador);

    /**
     * Método para efetuar uma nova jogada
     *
     * @param partidaDTO DTO contendo os dados da jogada
     * @return CustomMessage contendo o status da jogada
     * @throws IOException exception IO
     */
    CustomMessageType efetuarJogada(@Valid PartidaDTO partidaDTO) throws IOException;

    /**
     * Método auxiliar para exibir o tabuleiro da partida no console
     *
     * @param id da partida
     */
    void exibirTabuleiroConsole(String id) throws IOException;
}
