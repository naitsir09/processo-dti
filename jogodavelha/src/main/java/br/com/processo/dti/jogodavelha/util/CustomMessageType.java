package br.com.processo.dti.jogodavelha.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe para Message genericas
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomMessageType {
    private String msg = "";
    private String winner = "";

    public CustomMessageType(String msg) {
        this.msg = msg;
    }
}
