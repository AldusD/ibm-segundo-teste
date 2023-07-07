package segundoteste;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Segundo {
    public Segundo() {
    }

    private List<Candidatura> candidatos = new ArrayList<Candidatura>();
    private int ultimoId = 0;

    private int gerarId() {
        this.ultimoId++;
        return this.ultimoId;
    }

    private Optional<Candidatura> encontraCandidatoPeloId(int codCandidato) {
        if (codCandidato <= 0) return Optional.empty();
        for (int i = 0; i < candidatos.size(); i++) {
            final Candidatura candidato = candidatos.get(i);
            if (candidato.getId() == codCandidato ) {
                return Optional.of(candidato);
            }
        }
        return Optional.empty();
    }

    private Optional<Candidatura> encontraCandidatoPeloNome(String nomeCandidato) {
        for (int i = 0; i < candidatos.size(); i++) {
            final Candidatura candidato = candidatos.get(i);
            if (candidato.getName().equals(nomeCandidato)) {
                return Optional.of(candidato);
            }
        }
        return Optional.empty();
    }

    public int iniciarProcesso(String nome) throws Exception {
        if (nome.isEmpty()) throw new Exception("Nome inválido");

        final Optional<Candidatura> verificaNome = encontraCandidatoPeloNome(nome);
        if (!verificaNome.isEmpty()) throw new Exception("Candidato já participa do processo");

        final int id = this.gerarId();
        Candidatura candidato = new Candidatura(id, nome, "Recebido");
        this.candidatos.add(candidato);
        return id;
    }

    public void marcarEntrevista(int codCandidato) throws Exception {
        final Optional<Candidatura> verificaCandidato = encontraCandidatoPeloId(codCandidato);
        if (verificaCandidato.isEmpty()) throw new Exception("Candidato não encontrado");

        final Candidatura candidato = verificaCandidato.get();
        if (candidato.getStatus() != "Recebido") throw new Exception("Candidato não encontrado");

        candidato.setStatus("Qualificado");
    }

    public void desqualificarCandidato(int codCandidato) throws Exception {
        final Optional<Candidatura> verificaCandidato = encontraCandidatoPeloId(codCandidato);
        if (verificaCandidato.isEmpty()) throw new Exception("Candidato não encontrado");

        final Candidatura candidato = verificaCandidato.get();
        candidatos.remove(candidato);
    }

    public String verificarStatusCandidato(int codCandidato) throws Exception {
        final Optional<Candidatura> verificaCandidato = encontraCandidatoPeloId(codCandidato);
        if (verificaCandidato.isEmpty()) throw new Exception("Candidato não encontrado");

        return verificaCandidato.get().getStatus();
    }

    public void aprovarCandidato(int codCandidato) throws Exception {
        final Optional<Candidatura> verificaCandidato = encontraCandidatoPeloId(codCandidato);
        if (verificaCandidato.isEmpty()) throw new Exception("Candidato não encontrado");

        final Candidatura candidato = verificaCandidato.get();
        if (candidato.getStatus() != "Qualificado") throw new Exception("Candidato não encontrado");

        candidato.setStatus("Aprovado");
        }

    public List<String> obterAprovados() {
        final ArrayList<String> aprovados = new ArrayList<>();
        for (int i = 0; i < candidatos.size(); i++) {
            final Candidatura candidato = candidatos.get(i);
            if (candidato.getStatus() == "Aprovado") {
                aprovados.add(candidato.getName());
            }
        }
        return aprovados;
    }
}
