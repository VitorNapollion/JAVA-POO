package br.ufpb.sistemaeleitoral;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.*;

public class SistemaEleitoralMain extends JFrame implements ActionListener {

    JButton b1 = new JButton("Votar");
    JButton b2 = new JButton("Obter Dados do Candidato");
    JButton b3 = new JButton("Contar Votos do Candidato");
    JButton b4 = new JButton("Cadastrar Candidato");
    JButton b5 = new JButton("Cadastrar Eleitor");
    ImageIcon Image = new ImageIcon(getClass().getResource("urna.jpg"));
    JLabel label = new JLabel(Image);
    JLabel texto = new JLabel("Bem Vindos ao Sistema Eleitoral POO");
    Font fonte = new Font("Serif", Font.BOLD, 20);

    public SistemaEleitoralMain() {
        
        add(label);

        //CONFIGURAÇÕES DA JANELA PRINCIPAL
        setTitle("Sistema Eleitoral POO");
        setSize(400, 400);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        //Botões
        b1.setBounds(165, 60, 70, 30);
        add(b1);
        b1.addActionListener(this);
        b2.setBounds(100, 102, 200, 30);
        add(b2);
        b2.addActionListener(this);
        b3.setBounds(100, 146, 200, 30);
        add(b3);
        b3.addActionListener(this);
        b4.setBounds(125, 194, 150, 30);
        add(b4);
        b4.addActionListener(this);
        b5.setBounds(125, 240, 150, 30);
        add(b5);
        b5.addActionListener(this);
        //Texto SistemaEleitoral POO
        texto.setBounds(25, 0, 350, 20);
        texto.setFont(fonte);
        add(texto);
        add(label);

    }


    public static void main(String[] args) {
        new SistemaEleitoralMain();

    
    }

    SistemaEleitoralMap sistema = new SistemaEleitoralMap();

    @Override
    public void actionPerformed(ActionEvent e){
        
        if (e.getSource() == (b1)) {
            String tituloEleitor = JOptionPane.showInputDialog("Digite seu titulo: ").toUpperCase();
            int numeroVotado = Integer.parseInt(JOptionPane.showInputDialog("Digite o número do Candidato: "));
            try {
                sistema.votar(tituloEleitor, numeroVotado);
                System.out.print("sucesso");
            } catch (TituloInexistenteException b) {
                JOptionPane.showMessageDialog(null, "Titulo não existe ou digitado errado");
                b.printStackTrace();
            }

        }


        if (e.getSource() == (b2)) {
            String nomeCandidato = JOptionPane.showInputDialog("Digite o nome do candidato: ").toUpperCase();
            try {
                Candidato obterDados = sistema.obterDadosDoCandidato(nomeCandidato);
                JOptionPane.showMessageDialog(null, obterDados);
                System.out.print("sucesso");

            } catch (CandidatoInexistenteException c) {
                JOptionPane.showMessageDialog(null, "Candidato não existe ou digitado errado");
                c.printStackTrace();
            }
        }


        if(e.getSource() == b3){
            int contarVotosParaCandidato = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o numero do candidato: "));
            int votos = sistema.contarVotosParaCandidato(contarVotosParaCandidato);
            if(votos == 0){
                JOptionPane.showMessageDialog(null, "Não foram encontrado votos ou Número do candidato errado.");
            } else{
                JOptionPane.showMessageDialog(null, votos);
            }
            
        }


        if(e.getSource() == b4){
            String nome = JOptionPane.showInputDialog("Digite os dados do Candidato. \n Nome:").toUpperCase();
            int numero = Integer.parseInt(JOptionPane.showInputDialog("Numero do Candidato: "));
            String partidos = JOptionPane.showInputDialog("PARTIDO1 \n PARTIDO2 \n PARTIDO3 \n Escolha um dos partidos: " ).toUpperCase();
            Partido partido = null;
            if(partidos.equals("PARTIDO1")){
                partido = Partido.PARTIDO1;
            } else if(partidos.equals("PARTIDO2")){
                partido = Partido.PARTIDO2;
            } else if(partidos.equals("PARTIDO3")){
                partido = Partido.PARTIDO3;
            }
            if(nome.equals("") || numero == 0 || partidos.equals("")){
                JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar o Candidato. Digite os dados corretos.");
            } else {
                sistema.cadastraCandidato(nome, numero, partido);
            }

            
        }

        else if(e.getSource() == b5){
            String nome = JOptionPane.showInputDialog("Digite o seu nome: ").toUpperCase();
            String titulo = JOptionPane.showInputDialog("Digite seu titulo: ").toUpperCase();
            if(nome.equals("") || titulo.equals("")){
                JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar o Eleitor. Digite os dados corretos.");
            }else {
                sistema.cadastraEleitor(nome, titulo);
            }
        }

        
    }

}