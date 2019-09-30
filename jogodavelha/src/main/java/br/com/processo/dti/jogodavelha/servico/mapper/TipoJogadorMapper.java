package br.com.processo.dti.jogodavelha.servico.mapper;

import br.com.processo.dti.jogodavelha.dominio.TipoJogador;
import br.com.processo.dti.jogodavelha.servico.dto.TipoJogadorDTO;
import org.mapstruct.Mapper;

/**
 * Classe de Mapper para o dominio TP_JOGADOR.
 */
@Mapper(componentModel = "spring")
public interface TipoJogadorMapper extends EntityMapper<TipoJogadorDTO, TipoJogador> {
}
