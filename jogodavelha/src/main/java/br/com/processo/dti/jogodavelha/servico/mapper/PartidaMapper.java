package br.com.processo.dti.jogodavelha.servico.mapper;

import br.com.processo.dti.jogodavelha.dominio.Partida;
import br.com.processo.dti.jogodavelha.servico.dto.PartidaDTO;
import org.mapstruct.Mapper;

/**
 * Classe de Mapper para o dominio PARTIDA.
 */
@Mapper(componentModel = "spring", uses = {TipoJogadorMapper.class})
public interface PartidaMapper extends EntityMapper<PartidaDTO, Partida> {
}
