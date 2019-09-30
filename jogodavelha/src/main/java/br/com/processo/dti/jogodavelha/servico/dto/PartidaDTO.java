package br.com.processo.dti.jogodavelha.servico.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Data Transfer Object referente a Partida de Jogo da Velha.
 */
@Getter
@Setter
@NoArgsConstructor
public class PartidaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    private String player;

    private PositionDTO position;
}
