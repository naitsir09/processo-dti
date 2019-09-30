package br.com.processo.dti.jogodavelha;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class JogoDaVelhaApplicationTest {

    private JogoDaVelhaApplication jogoDaVelhaApplication;

    @Mock
    private Environment env;

    @Before
    public void setUp() throws Exception {
        jogoDaVelhaApplication = new JogoDaVelhaApplication();
    }

    @Test
    public void infoAppStart() {
        Mockito.when(env.getActiveProfiles()).thenReturn(new String[]{"dev"});
        jogoDaVelhaApplication.infoAppStart(env, "http", "localhost");
        assertThat(env.getActiveProfiles()).isNotNull();
    }

}
