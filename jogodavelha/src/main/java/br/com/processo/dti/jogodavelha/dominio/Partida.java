package br.com.processo.dti.jogodavelha.dominio;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;

/**
 * Classe de Domínio PARTIDA.
 */
@Entity
@Table(name = "PARTIDA")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Partida implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", nullable = false)
    private String id;

    @JoinColumn(name = "ID_TP_JOGADOR_FIRST", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoJogador tipoJogadorFirst;

    @JoinColumn(name = "ID_TP_JOGADOR_ATUAL", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoJogador tipoJogadorAtual;

    @Column(name = "JOGADOR_VENCEDOR")
    private String jogadorVencedor;

    @Column(name = "DATA_CRIACAO", nullable = false)
    private Instant dataCriacao;

    @Column(name = "DATA_FIM")
    private Instant dataFim;

    @Column(name = "IS_CONCLUIDO")
    private Boolean isConcluido;

    @Column(name = "CAMINHO_ARQUIVO_JOGO")
    private String caminhoArquivoJogo;
}
