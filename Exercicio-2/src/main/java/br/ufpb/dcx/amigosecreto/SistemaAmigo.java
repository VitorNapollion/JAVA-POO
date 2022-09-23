package br.ufpb.dcx.amigosecreto;

import java.util.ArrayList;
import java.util.List;

public class SistemaAmigo {

    private List<Mensagem> mensagens;
    private List<Amigo> amigos;
    private int maxM;

    public static final int MAX_MSG = 1000;

    public SistemaAmigo(int maxM){
        this.mensagens = new ArrayList<>();
        this.amigos = new ArrayList<>();
        this.maxM = maxM;
        
    }
    public SistemaAmigo(){
        this(MAX_MSG);
    }

    public List<Mensagem> getMensagens(){
        return this.mensagens;
    }

    public List<Amigo> getAmigos(){
        return this.amigos;
    }

    public int getMaxM(){
        return this.maxM;
    }

    public void cadastraAmigo(String nomeAmigo, String emailAmigo) throws AmigoJaExisteException {
        Amigo amg = new Amigo(nomeAmigo, emailAmigo, emailAmigo);
        if(amg.getEmail().equalsIgnoreCase(emailAmigo)){
            this.amigos.add(amg);
        }

    }
    public Amigo pesquisaAmigo(String emailAmigo) throws AmigoInexistenteException{
        for(Amigo amg : amigos ){
            if(amg.getEmail().equalsIgnoreCase(emailAmigo));
            return amg;    
        }
        throw new AmigoInexistenteException("Amigo inexistente");
    }
    public void enviarMensagemParaTodos(String texto, String emailRemetente, boolean ehAnonima) {
		this.mensagens.add(new MensagemParaTodos(texto, emailRemetente, ehAnonima));
	}
    public void enviarMensagemParaAlguem(String texto, String emailRemetente, String emailDestinatario, boolean ehAnonima) {
		this.mensagens.add(new MensagemParaAlguem(texto, emailRemetente, emailDestinatario, ehAnonima));
	}
    public List <Mensagem> pesquisaMensagensAnonimas(){
        ArrayList <Mensagem> mensagensAnonimas = new ArrayList<>();

        for(Mensagem msg : mensagens){
            if(msg.ehAnonima()){
                mensagensAnonimas.add(msg);
            }
        }
        return mensagensAnonimas;
    }
    public List<Mensagem> pesquisaTodasAsMensagens(){
        return mensagens;
    }
    public void configuraAmigoSecretoDe(String emailDaPessoa, String emailAmigoSorteado) throws AmigoInexistenteException{
        for (Amigo amg: this.amigos){
            if(amg.getEmail().equals(emailDaPessoa)){
            amg.setEmailAmigoSorteado(emailAmigoSorteado);
            return;
            }
        }
        throw new AmigoInexistenteException("não existe pessoa no sistema com email" + emailDaPessoa);
    }
    public String pesquisaAmigoSecretoDe(String emailDaPessoa) throws AmigoInexistenteException, AmigoNaoSorteadoException{
        for(Amigo a : amigos){
            if(a.getEmail().equalsIgnoreCase(emailDaPessoa)){
                String emailAmigoSorteado = a.getEmailAmigoSorteado();
                if(emailAmigoSorteado == null){
                    throw new AmigoNaoSorteadoException("Amigo não está configurado");

                } else{
                    return emailAmigoSorteado;
                }
            }      
        }
        throw new AmigoInexistenteException("Não existe amigo");
        }
}