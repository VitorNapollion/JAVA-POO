package br.ufpb.sistemaeleitoral;

public class CandidatoInexistenteException extends Exception {
    public CandidatoInexistenteException(String message) {
        super(message);
    }

}
