package br.ufpb.dcx.amigosecreto;


import java.util.List;

import javax.swing.JOptionPane;

public class TestaSistemaGui {

    public static void main(String[] args){
        int maxM = Integer.parseInt(JOptionPane.showInputDialog("Qual a quantidade de mensagens"));
        SistemaAmigo sistema = new SistemaAmigo(maxM);

        int quantP = Integer.parseInt(JOptionPane.showInputDialog("Qual a quantidade de amigos"));

        for(int i = 0; i < quantP; i++){
            String nome = JOptionPane.showInputDialog("Qual o nome");
            String email = JOptionPane.showInputDialog("Qual o email");

            sistema.cadastraAmigo(nome, email);


        }
        List<Amigo> amigos = sistema.pesquisaAmigo();
        for (Amigo a : amigos) {
			String emailSorteado = JOptionPane.showInputDialog("Quem é o amigo de? " + a.getEmail());
			
			try {
				sistema.configuraAmigoSecretoDe(a.getEmail(), emailSorteado);
			} catch (AmigoInexistenteException e) {
				JOptionPane.showMessageDialog(null, "Problema encontrado");
			}
		}
        String texto = JOptionPane.showInputDialog("Qual a mensagem? ");
		String emailAdicionado = JOptionPane.showInputDialog("Qual o email que foi registrado? ");
		String mensagemAnonima = JOptionPane.showInputDialog("Sua mensagem é anonima? Sim (S) ou Não (N)?");
		boolean ehAnonima;
		
		if (mensagemAnonima.equalsIgnoreCase("S")) {
			ehAnonima = true;
		} else {
			ehAnonima = false;
		}
		
		sistema.enviarMensagemParaTodos(texto, emailAdicionado, ehAnonima);

    }

   
    
}
