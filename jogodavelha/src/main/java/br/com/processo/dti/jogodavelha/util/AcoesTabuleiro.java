package br.com.processo.dti.jogodavelha.util;

/**
 * Classe com ações feitas no tabuleiro
 */
public class AcoesTabuleiro {

    /**
     * Checar se o tabuleiro já possui vencedor
     *
     * @param tabuleiro matriz do tabuleiro
     * @return 1 para vitoria do O, 2 para vitoria do X, 0 para nenhuma vitoria
     */
    public int checarGanhador(int[][] tabuleiro) {
        if (this.checarLinhas(tabuleiro) == 1 ||
                this.checarColunas(tabuleiro) == 1 ||
                this.checarDiagonais(tabuleiro) == 1
        ) {
            return 1;
        }
        if (this.checarLinhas(tabuleiro) == 2 ||
                this.checarColunas(tabuleiro) == 2 ||
                this.checarDiagonais(tabuleiro) == 2
        ) {
            return 2;
        }
        return 0;
    }

    /**
     * Método para checar se o tabuleiro "deu" velha
     *
     * @param tabuleiro matriz do tabuleiro
     * @return true se houver velha, false se não
     */
    public boolean checarVelha(int[][] tabuleiro) {
        for (int linha = 0; linha < 3; linha++) {
            for (int coluna = 0; coluna < 3; coluna++) {
                if (tabuleiro[linha][coluna] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Método para checar se há vitória em alguma das linhas
     *
     * @param tabuleiro matriz do tabuleiro
     * @return 1 para vitória do O, 2 para vitória do X, 0 para nenhuma vitória
     */
    private int checarLinhas(int[][] tabuleiro) {
        for (int linha = 0; linha < 3; linha++) {
            if ((tabuleiro[linha][0] + tabuleiro[linha][1] + tabuleiro[linha][2]) == 3) {
                return 1;
            }
            if ((tabuleiro[linha][0] + tabuleiro[linha][1] + tabuleiro[linha][2]) == -3) {
                return 2;
            }
        }
        return 0;
    }

    /**
     * Método para checar se há vitória em alguma das colunas
     *
     * @param tabuleiro matriz do tabuleiro
     * @return 1 para vitória do O, 2 para vitória do X, 0 para nenhuma vitória
     */
    private int checarColunas(int[][] tabuleiro) {
        for (int coluna = 0; coluna < 3; coluna++) {
            if ((tabuleiro[0][coluna] + tabuleiro[1][coluna] + tabuleiro[2][coluna]) == 3) {
                return 1;
            }
            if ((tabuleiro[0][coluna] + tabuleiro[1][coluna] + tabuleiro[2][coluna]) == -3) {
                return 2;
            }
        }
        return 0;
    }

    /**
     * Método para checar se há vitória em alguma das diagonais
     *
     * @param tabuleiro matriz do tabuleiro
     * @return 1 para vitória do O, 2 para vitória do X, 0 para nenhuma vitória
     */
    private int checarDiagonais(int[][] tabuleiro) {
        if ((tabuleiro[0][0] + tabuleiro[1][1] + tabuleiro[2][2]) == 3 ||
                (tabuleiro[0][2] + tabuleiro[1][1] + tabuleiro[2][0]) == 3) {
            return 1;
        }
        if ((tabuleiro[0][0] + tabuleiro[1][1] + tabuleiro[2][2]) == -3 ||
                (tabuleiro[0][2] + tabuleiro[1][1] + tabuleiro[2][0]) == -3) {
            return 2;
        }
        return 0;
    }

    /**
     * Métodos simples para exibir o status atual do tabuleiro no console
     *
     * @param tabuleiro matriz do tabuleiro
     */
    /* TODO refactor to for x and y */
    public void exibirTabuleiroConsole(int[][] tabuleiro) {
        System.out.println((tabuleiro[0][2] == 0 ? "x(0)y(2)" : tabuleiro[0][2] == 1 ? "O" : "X") + "|" +
                (tabuleiro[1][2] == 0 ? "x(1)y(2)" : tabuleiro[1][2] == 1 ? "O" : "X") + "|" +
                (tabuleiro[2][2] == 0 ? "x(2)y(2)" : tabuleiro[2][2] == 1 ? "O" : "X"));
        System.out.println((tabuleiro[0][1] == 0 ? "x(0)y(1)" : tabuleiro[0][1] == 1 ? "O" : "X") + "|" +
                (tabuleiro[1][1] == 0 ? "x(1)y(1)" : tabuleiro[1][1] == 1 ? "O" : "X") + "|" +
                (tabuleiro[2][1] == 0 ? "x(2)y(1)" : tabuleiro[2][1] == 1 ? "O" : "X"));
        System.out.println((tabuleiro[0][0] == 0 ? "x(0)y(0)" : tabuleiro[0][0] == 1 ? "O" : "X") + "|" +
                (tabuleiro[1][0] == 0 ? "x(1)y(0)" : tabuleiro[1][0] == 1 ? "O" : "X") + "|" +
                (tabuleiro[2][0] == 0 ? "x(2)y(0)" : tabuleiro[2][0] == 1 ? "O" : "X"));
    }
}
