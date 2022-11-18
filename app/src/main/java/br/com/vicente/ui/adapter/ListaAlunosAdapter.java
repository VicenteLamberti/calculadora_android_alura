package br.com.vicente.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.vicente.R;
import br.com.vicente.model.Aluno;
import br.com.vicente.ui.activity.ListaAlunosActivity;

public class ListaAlunosAdapter extends BaseAdapter {

    private final List<Aluno> alunos = new ArrayList<>();
    private Context context;

    public ListaAlunosAdapter(Context context){
        this.context = context;
    }
    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewCriada = LayoutInflater.from(context)
                .inflate(R.layout.item_aluno, parent, false);

        Aluno alunoDevolvido = alunos.get(position);
        TextView nomeAluno = viewCriada.findViewById(R.id.item_aluno_nome);
        nomeAluno.setText(alunoDevolvido.getNomeAluno());

        TextView telefoneAluno = viewCriada.findViewById(R.id.item_aluno_telefone);
        telefoneAluno.setText(alunoDevolvido.getTelefoneAluno());
        return viewCriada;
    }

    public void clear() {
        alunos.clear();
    }

    public void addAll(List<Aluno> alunos) {
        this.alunos.addAll(alunos);
    }

    public void remove(Aluno aluno) {
        alunos.remove(aluno);
    }
}