package br.ufpb.dcx.amigosecreto;

public class MensagemParaAlguem extends Mensagem{

    private String emailDestinatario;

    public MensagemParaAlguem(String texto, String emailRemetente, String emailDestinatario, boolean anonima ){
        super(texto, emailRemetente, anonima);
        this.emailDestinatario = emailRemetente;

    }
    public String getEmailDestinatario(){

        return this.emailDestinatario;
    }
    public void setEmailDestinatario(String emailDestinatario){

        this.emailDestinatario = emailDestinatario;
    }
    @Override
	public String getTextoCompletoAExibir() {
		if(super.ehAnonima()) {
			return "Mensagem para Alguém: " + getTexto();
		}
        else{
            return "Mensagem de " + getEmailRemetente() + "para" + getEmailDestinatario() + "texto" + getTexto();
        } 
    }
}
