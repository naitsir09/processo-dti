package br.com.processo.dti.jogodavelha.repositorio;

import br.com.processo.dti.jogodavelha.dominio.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Classe de Repositório referente ao dominio Partida.
 */
@Repository
public interface PartidaRepositorio extends JpaRepository<Partida, String> {
    /**
     * Query para checar se a Partida existe
     *
     * @param idPartida UUID da partida
     * @return true se existir, false caso não
     */
    boolean existsById(String idPartida);

    /**
     * Query para checar se a Partida não foi concluida
     *
     * @param idPartida UUID da partida
     * @return true se sim, false caso não
     */
    boolean existsByIdAndIsConcluidoIsTrue(String idPartida);

    /**
     * Query para checar se é a vez do jogador atual
     *
     * @param idPartida UUID da partida
     * @param idJogador id (1 = O, 2 = X) do jogador
     * @return true se for a vez, false se não
     */
    boolean existsByIdAndTipoJogadorAtualId(String idPartida, Long idJogador);
}
