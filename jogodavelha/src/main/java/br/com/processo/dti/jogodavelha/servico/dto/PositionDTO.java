package br.com.processo.dti.jogodavelha.servico.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Data Transfer Object referente a Posição de Jogo da Velha.
 */
@Getter
@Setter
@NoArgsConstructor
public class PositionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer x;

    private Integer y;
}
