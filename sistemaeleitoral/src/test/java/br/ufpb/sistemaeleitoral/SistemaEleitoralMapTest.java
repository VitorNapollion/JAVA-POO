package br.ufpb.sistemaeleitoral;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


class SistemaEleitoralMapTest {

    SistemaEleitoralMap sistema = new SistemaEleitoralMap();
    
    @Test
    public void testCadastraCandidato() {
        boolean candidato1 = sistema.cadastraCandidato("Vitão", 666, Partido.PARTIDO2);
        assertTrue(candidato1);
    }

    @Test
    public void testCadastraEleitor() {
        boolean eleitor1 = sistema.cadastraEleitor("Ronaldete", "24");
        assertTrue(eleitor1);
        boolean eleitor2 = sistema.cadastraEleitor("Ronaldete", "24");
        assertFalse(eleitor2);
    }
    
    @Test
    public void testVoto() throws TituloInexistenteException{
        boolean candidato1 = sistema.cadastraCandidato("Vitão", 666, Partido.PARTIDO2);
        boolean eleitor1 = sistema.cadastraEleitor("Ronaldete", "24");
        try {
            sistema.votar("24", 666);
        } catch (TituloInexistenteException e) {
            fail("Não deveria lançar exceção");
        }
    }

    @Test
    public void testContarVotos() throws TituloInexistenteException{
        boolean candidato1 = sistema.cadastraCandidato("Vitão", 666, Partido.PARTIDO2);
        boolean eleitor1 = sistema.cadastraEleitor("Ronaldinha", "2424");
        sistema.votar("2424", 666);
    
        int contarVotos = sistema.contarVotosParaCandidato(666);
        assertEquals(1, contarVotos);
        assertTrue(contarVotos == 1);        
    }

    @Test
    public void testObterDadosDoCandidato() throws CandidatoInexistenteException{
        boolean candidato1 = sistema.cadastraCandidato("Vitão", 666, Partido.PARTIDO2);
        
        try {
            Candidato obterDados = sistema.obterDadosDoCandidato("Vitão"); 
            assertEquals("Vitão", obterDados.getNome());      
        } catch (CandidatoInexistenteException e) {
            fail("Não deveria lançar exceção");
        }
    }
}

