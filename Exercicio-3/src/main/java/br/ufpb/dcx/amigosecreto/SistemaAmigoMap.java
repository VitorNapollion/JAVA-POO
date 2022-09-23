package br.ufpb.dcx.amigosecreto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaAmigoMap {

	private Map<String, Amigo> amigos;
	private List<Mensagem> mensagens;
	private int maxM;
	

	public static final int MAX_MSG = 1000;

	public SistemaAmigoMap(int maxM) {
		this.amigos = new HashMap<>();
		this.mensagens = new ArrayList<>();
	}

	public SistemaAmigoMap() {
		this(MAX_MSG);
	}

	public Map<String, Amigo> getAmigos() {
		return amigos;
	}

	public List<Mensagem> getMensagem() {
		return mensagens;
	}

	public int getMaxM() {
		return maxM;
	}

	public void cadastraAmigo(String nomeAmigo, String emailAmigo) throws AmigoJaExisteException {
		Amigo amg = new Amigo(nomeAmigo, emailAmigo, emailAmigo);

		if (this.amigos.containsKey(emailAmigo)) {
			throw new AmigoJaExisteException("Esse amigo já existe");
		}
		this.amigos.put(emailAmigo, amg);
	}

	public Amigo pesquisaAmigo(String emailAmigo) throws AmigoInexistenteException {
		Amigo amigoE = this.amigos.get(emailAmigo);

		if (amigoE == null) {
			throw new AmigoInexistenteException("Amigo não está registrado");
		}

		return amigoE;
	}

	public String pesquisaAmigoSecretoDe(String emailDaPessoa) throws AmigoInexistenteException, AmigoNaoSorteadoException {

		for(Amigo a : this.amigos.values()){
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

	public void configuraAmigoSecretoDe(String emailDaPessoa, String emailAmigoSorteado) throws AmigoNaoSorteadoException, AmigoInexistenteException {
		
		for (Amigo amg: this.amigos.values()){
            if(amg.getEmail().equals(emailDaPessoa)){
				amg.setEmailAmigoSorteado(emailAmigoSorteado);
				return;
            }
        }
        throw new AmigoInexistenteException("não existe pessoa no sistema com email" + emailDaPessoa);
    }
		

	
	public void enviarMensagemParaTodos(String nome, String email, boolean ehAnonima) {
		this.mensagens.add(new MensagemParaTodos(nome, email, ehAnonima));
	}
	
	public void enviarMensagemParaAlguem(String texto, String emailRemetente, String emailDestinatario, boolean ehAnonima) {
		this.mensagens.add(new MensagemParaAlguem(texto, emailRemetente, emailDestinatario, ehAnonima));
		
	}
	
	public List<Mensagem> pesquisaTodasAsMensagens() {
		return this.mensagens;
	}
	
	public List<Mensagem> pesquisaMensagensAnonimas() {
		List<Mensagem> msgAnonimas = new ArrayList<>();
		
		for(Mensagem msg : this.mensagens){
            if(msg.ehAnonima()){
                msgAnonimas.add(msg);
            }
        }
        return msgAnonimas;
    }
}
