package br.com.processo.dti.jogodavelha.servico.impl;

import br.com.processo.dti.jogodavelha.dominio.Partida;
import br.com.processo.dti.jogodavelha.dominio.TipoJogador;
import br.com.processo.dti.jogodavelha.repositorio.PartidaRepositorio;
import br.com.processo.dti.jogodavelha.servico.JogoDaVelhaServico;
import br.com.processo.dti.jogodavelha.servico.dto.NovaPartidaDTO;
import br.com.processo.dti.jogodavelha.servico.dto.PartidaDTO;
import br.com.processo.dti.jogodavelha.servico.mapper.NovaPartidaMapper;
import br.com.processo.dti.jogodavelha.util.AcoesTabuleiro;
import br.com.processo.dti.jogodavelha.util.CustomMessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.Random;
import java.util.UUID;

/**
 * Classe de Implementação do Serviço JogoDaVelhaServico.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class JogoDaVelhaServicoImpl implements JogoDaVelhaServico {

    private static final String CAMINHO_JOGOS = "src/main/resources/jogos/";

    private final PartidaRepositorio partidaRepositorio;

    private final NovaPartidaMapper novaPartidaMapper;

    private final AcoesTabuleiro acoesTabuleiro = new AcoesTabuleiro();

    /**
     * {@inheritDoc}
     */
    @Override
    public NovaPartidaDTO novoJogo() throws IOException {
        Partida partida = criarNovaPartida();
        partida = this.partidaRepositorio.saveAndFlush(partida);
        return this.novaPartidaMapper.toDto(partida);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checarPartidaInexistente(String idPartida) {
        return !this.partidaRepositorio.existsById(idPartida);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checarPartidaConcluida(String idPartida) {
        return this.partidaRepositorio.existsByIdAndIsConcluidoIsTrue(idPartida);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checarTurnoJogador(String idPartida, Long idJogador) {
        return this.partidaRepositorio.existsByIdAndTipoJogadorAtualId(idPartida, idJogador);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomMessageType efetuarJogada(@Valid PartidaDTO partidaDTO) throws IOException {
        int[][] tabuleiro = new int[3][3];
        CustomMessageType customMessageType = new CustomMessageType();

        Partida partida = this.partidaRepositorio.findById(partidaDTO.getId()).get();

        preencherMatrizTabuleiro(tabuleiro, partida.getCaminhoArquivoJogo());
        gravarJogadaMatriz(partidaDTO, tabuleiro, partida);

        partida.setTipoJogadorAtual(new TipoJogador(partida.getTipoJogadorAtual().getId().equals(1L) ? 2L : 1L));
        if (checarStatusPartida(tabuleiro, customMessageType, partida)) return customMessageType;

        this.partidaRepositorio.saveAndFlush(partida);

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exibirTabuleiroConsole(String id) throws IOException {
        if(this.partidaRepositorio.findById(id).isPresent()){
            int[][] tabuleiro = new int[3][3];
            Partida partida = this.partidaRepositorio.findById(id).get();
            preencherMatrizTabuleiro(tabuleiro, partida.getCaminhoArquivoJogo());
            System.out.println("ID da partida: " + partida.getId());
            acoesTabuleiro.exibirTabuleiroConsole(tabuleiro);
        }
    }

    /**
     * Método para checar se a partida finalizou com vitória ou empate
     *
     * @param tabuleiro         matriz do tabuleiro
     * @param customMessageType classe de mensagens genericas
     * @param partida entidade partida
     * @return true caso a partida tenha finalizado, false caso não
     */
    private boolean checarStatusPartida(int[][] tabuleiro, CustomMessageType customMessageType, Partida partida) {
        if (acoesTabuleiro.checarVelha(tabuleiro)) {
            customMessageType.setWinner("Draw");
            setDadosFinaisPartida(partida, "D");
            return true;
        }
        if (acoesTabuleiro.checarGanhador(tabuleiro) == 1) {
            customMessageType.setMsg("Partida finalizada");
            customMessageType.setWinner("O");
            setDadosFinaisPartida(partida, "O");
            return true;

        } else if (acoesTabuleiro.checarGanhador(tabuleiro) == 2) {
            customMessageType.setMsg("Partida finalizada");
            customMessageType.setWinner("X");
            setDadosFinaisPartida(partida, "X");
            return true;
        }
        return false;
    }

    /**
     * Método para setar a conclusão, data fim e quem ganhou a partida
     * @param partida entidade partida
     * @param vencedor quem venceu a partida (D = empate, X, O)
     */
    private void setDadosFinaisPartida(Partida partida, String vencedor) {
        partida.setIsConcluido(true);
        partida.setDataFim(Instant.now());
        partida.setJogadorVencedor(vencedor);
    }

    /**
     * Método para gravar no arquivo a matriz preenchida com a jogada.
     *
     * @param partidaDTO DTO contendo os dados necessários
     * @param tabuleiro  matriz do tabuleiro
     * @param partida    entidade vindo do banco
     * @throws IOException exceptio IO
     */
    private void gravarJogadaMatriz(@Valid PartidaDTO partidaDTO, int[][] tabuleiro, Partida partida) throws IOException {
        FileWriter fileWriter = new FileWriter(partida.getCaminhoArquivoJogo());
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        tabuleiro[partidaDTO.getPosition().getX()][partidaDTO.getPosition().getY()] = partidaDTO.getPlayer().equalsIgnoreCase("O") ? 1 : -1;
        for (int posX = 0; posX < 3; posX++) {
            for (int posY = 2; posY >= 0; posY--) {
                bufferedWriter.write(String.valueOf(tabuleiro[posX][posY]));
                bufferedWriter.newLine();
            }
        }
        bufferedWriter.close();
    }

    /**
     * Método para preencher a matriz do tabuleiro com os dados salvos no arquivo
     *
     * @param tabuleiro          matriz do tabuleiro
     * @param caminhoArquivoJogo caminho aonde está o arquivo com os dados
     * @throws IOException exception IO
     */
    private void preencherMatrizTabuleiro(int[][] tabuleiro, String caminhoArquivoJogo) throws IOException {
        FileReader fileReader = new FileReader(caminhoArquivoJogo);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String linha = bufferedReader.readLine();
        while (linha != null) {
            for (int posX = 0; posX < 3; posX++) {
                for (int posY = 2; posY >= 0; posY--) {
                    tabuleiro[posX][posY] = Integer.parseInt(linha);
                    linha = bufferedReader.readLine();
                }
            }
        }
    }

    /**
     * Método para criar um objeto partida com os valores pré-definidos
     *
     * @return instância da classe Partida com os atributos
     */
    private Partida criarNovaPartida() throws IOException {
        Partida partida = new Partida();
        partida.setId(UUID.randomUUID().toString());
        partida.setDataCriacao(Instant.now());
        partida.setTipoJogadorFirst(new TipoJogador(Long.valueOf(new Random().nextInt(2) + 1)));
        partida.setTipoJogadorAtual(partida.getTipoJogadorFirst());
        partida.setCaminhoArquivoJogo(CAMINHO_JOGOS + partida.getId() + ".txt");
        File file = new File(partida.getCaminhoArquivoJogo());
        file.createNewFile();
        return partida;
    }
}
