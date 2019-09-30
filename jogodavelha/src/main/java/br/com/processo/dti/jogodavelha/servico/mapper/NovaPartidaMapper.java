package br.com.processo.dti.jogodavelha.servico.mapper;

import br.com.processo.dti.jogodavelha.dominio.Partida;
import br.com.processo.dti.jogodavelha.servico.dto.NovaPartidaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Classe de Mapper para o dominio PARTIDA.
 */
@Mapper(componentModel = "spring", uses = {TipoJogadorMapper.class})
public interface NovaPartidaMapper extends EntityMapper<NovaPartidaDTO, Partida> {
    /**
     * {@inheritDoc}
     */
    @Override
    @Mapping(source = "tipoJogadorFirst.descricao", target = "firstPlayer")
    NovaPartidaDTO toDto(Partida entity);
}
