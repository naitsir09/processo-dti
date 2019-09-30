package br.com.processo.dti.jogodavelha.dominio;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Classe de Domínio TipoJogador.
 */
@Entity
@Table(name = "TP_JOGADOR")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@NoArgsConstructor
public class TipoJogador extends TipoDominioFixo {
    public TipoJogador(Long id) {
        super(id);
    }
}
