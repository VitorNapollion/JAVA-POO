package br.ufpb.dcx.amigosecreto;

import java.util.List;

public class TestaSistemaAmigo {
    public static void main(String[] args) throws Exception{
        SistemaAmigo Bs = new SistemaAmigo();
        
        Bs.cadastraAmigo("José", "jose@gmail.com");
        Bs.cadastraAmigo("Maria", "maria@gmail.com");

        try{
            Bs.configuraAmigoSecretoDe("jose@gmail.com", "maria@gmail.com");
            Bs.configuraAmigoSecretoDe("maria@gmail.com", "jose@gmail.com");
            System.out.println("Sucesso");

        } catch(AmigoInexistenteException e){
            System.out.println(e.getMessage());
        }

        Bs.enviarMensagemParaAlguem("Bom dia meu brodi", "maria@gmail.com", "jose@gmail.com", true);
        Bs.enviarMensagemParaTodos("Bom dia meus queridos", "maria@gmail.com", true);

        List<Mensagem> msg = Bs.pesquisaMensagensAnonimas();

		for (Mensagem m : msg) {
			System.out.println(m.getTextoCompletoAExibir());
		}

		try {
			Bs.pesquisaAmigoSecretoDe("jose@gmail.com");
			System.out.println("OK");
		} catch (AmigoNaoSorteadoException e) {
			System.out.println(e.getMessage());
		} catch (AmigoInexistenteException e) {
			System.out.println(e.getMessage());
		}




    }
    
}
