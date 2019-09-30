DROP TABLE IF EXISTS TP_JOGADOR;
CREATE TABLE TP_JOGADOR (
                         ID number NOT NULL,
                         DESCRICAO CHAR NOT NULL,
                         PRIMARY KEY ( ID )
);
COMMENT ON TABLE TP_JOGADOR IS 'Tabela referente ao dominio "TP_JOGADOR"';
COMMENT ON COLUMN TP_JOGADOR.ID IS 'Chave primária da tabela TP_JOGADOR';
COMMENT ON COLUMN TP_JOGADOR.DESCRICAO IS 'Descrição do TP, 1 = "O", 2 = "X"';

INSERT INTO TP_JOGADOR (ID, DESCRICAO) VALUES (1, 'O');
INSERT INTO TP_JOGADOR (ID, DESCRICAO) VALUES (2, 'X');

-- FIM TP_JOGADOR

DROP TABLE IF EXISTS PARTIDA;
CREATE TABLE PARTIDA (
ID VARCHAR2(36 CHAR) NOT NULL,
ID_TP_JOGADOR_FIRST NUMBER NOT NULL,
ID_TP_JOGADOR_ATUAL NUMBER NOT NULL,
DATA_CRIACAO TIMESTAMP NOT NULL,
DATA_FIM TIMESTAMP,
IS_CONCLUIDO BOOLEAN DEFAULT FALSE,
JOGADOR_VENCEDOR VARCHAR2(1 CHAR),
CAMINHO_ARQUIVO_JOGO VARCHAR2,
PRIMARY KEY ( ID ),
FOREIGN KEY ( ID_TP_JOGADOR_FIRST ) REFERENCES TP_JOGADOR( ID ),
FOREIGN KEY ( ID_TP_JOGADOR_ATUAL ) REFERENCES TP_JOGADOR( ID )
);
COMMENT ON TABLE PARTIDA IS 'Tabela referente ao dominio "PARTIDA"';
COMMENT ON COLUMN PARTIDA.ID IS 'Chave primária (UUID) da tabela PARTIDA';
COMMENT ON COLUMN PARTIDA.ID_TP_JOGADOR_FIRST IS 'FK referente ao domínio fixo JOGADOR para definir quem irá começar';
COMMENT ON COLUMN PARTIDA.ID_TP_JOGADOR_ATUAL IS 'FK referente ao domínio fixo JOGADOR para definir de quem é a vez';
COMMENT ON COLUMN PARTIDA.DATA_CRIACAO IS 'Data da criação da PARTIDA';
COMMENT ON COLUMN PARTIDA.DATA_FIM IS 'Data do fim da PARTIDA';
COMMENT ON COLUMN PARTIDA.IS_CONCLUIDO IS 'Flag para marcar a conclusão da PARTIDA';
COMMENT ON COLUMN PARTIDA.JOGADOR_VENCEDOR IS 'Guarda quem foi o vencedor da PARTIDA';
COMMENT ON COLUMN PARTIDA.CAMINHO_ARQUIVO_JOGO IS 'Caminho do arquivo que guardará as informações do jogo';

-- FIM PARTIDA
