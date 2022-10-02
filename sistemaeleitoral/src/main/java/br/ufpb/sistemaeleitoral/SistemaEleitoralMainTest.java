package br.ufpb.sistemaeleitoral;

public class SistemaEleitoralMainTest {

    public static void main(String[] args) throws TituloInexistenteException, CandidatoInexistenteException{
        SistemaEleitoralMap sistema = new SistemaEleitoralMap();

        sistema.cadastraEleitor("vitor", "666");
        sistema.cadastraCandidato("Ronaldo", 2424, Partido.PARTIDO2);
        sistema.votar("666", 2424);
        System.out.println(sistema.obterDadosDoCandidato("Ronaldo"));
        System.out.println(sistema.contarVotosParaCandidato(2424));
    }
    
}
