package br.com.processo.dti.jogodavelha.servico.mapper;

import java.util.List;

/**
 * Classe generica para mapper de DTO para Entidade/Entidade para DTO.
 *
 * @param <D> - DTO
 * @param <E> - Entidade
 */

public interface EntityMapper<D, E> {

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntity(List<D> dtoList);

    List<D> toDto(List<E> entityList);
}
