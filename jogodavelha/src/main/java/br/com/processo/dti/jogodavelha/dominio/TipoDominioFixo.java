package br.com.processo.dti.jogodavelha.dominio;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Classe generica para dominio fixo.
 */
@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class TipoDominioFixo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    private String descricao;

    public TipoDominioFixo(Long id) {
        this.id = id;
    }
}
