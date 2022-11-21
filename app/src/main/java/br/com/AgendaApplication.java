package br.com;

import android.app.Application;

import br.com.vicente.dao.AlunoDAO;
import br.com.vicente.model.Aluno;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        inicializaListaAlunosTeste();
    }

    private void inicializaListaAlunosTeste() {
        AlunoDAO alunoDao = new AlunoDAO();
        for (int i = 0; i < 5; i++) {
            alunoDao.salvar(new Aluno("vicente","9999999","sjaisaojsao"));

        }
    }
}
