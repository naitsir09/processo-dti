package br.com.processo.dti.jogodavelha.servico.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * Data Transfer Object referente ao TP_JOGADOR
 */
@Getter
@Setter
@NoArgsConstructor
public class TipoJogadorDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String descricao;
}