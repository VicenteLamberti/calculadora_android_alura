package br.com.vicente.dao;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import br.com.vicente.model.Aluno;

public class AlunoDAO {

    private final static List<Aluno> listaAlunos = new ArrayList<>();
    private static int contadorId = 1;

    public void salvar(Aluno aluno) {
        aluno.setId(contadorId);
        contadorId++;
        atualizarID(aluno);

    }

    private void atualizarID(Aluno aluno) {
        listaAlunos.add(aluno);
    }

    public List<Aluno> buscarAlunos() {
        return new ArrayList<>(listaAlunos);
    }

    public void editar(Aluno aluno){
        Aluno alunoEncontrado = buscarAlunoPeloId(aluno);
        if(alunoEncontrado != null){
            int posicao = listaAlunos.indexOf(alunoEncontrado);
            listaAlunos.set(posicao,aluno);
        }

    }

    @Nullable
    private Aluno buscarAlunoPeloId(Aluno aluno) {
        Aluno alunoEncontrado = null;

        for (Aluno a: listaAlunos) {
            if(aluno.getId() == a.getId() ){
                alunoEncontrado = a;
                break;
            }

        }
        return alunoEncontrado;
    }

    public void deletar(Aluno alunoSelecionado) {
        Aluno aluno = buscarAlunoPeloId(alunoSelecionado);
        if(aluno != null){
            listaAlunos.remove(aluno);

        }

    }
}
