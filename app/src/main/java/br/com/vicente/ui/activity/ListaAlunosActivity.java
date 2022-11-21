package br.com.vicente.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.vicente.R;
import br.com.vicente.dao.AlunoDAO;
import br.com.vicente.model.Aluno;
import br.com.vicente.ui.adapter.ListaAlunosAdapter;

public class ListaAlunosActivity extends AppCompatActivity {
    public static final String TITULO_APP_BAR = "Lista de alunos";

    private final AlunoDAO alunoDao = new AlunoDAO();
    private ListaAlunosAdapter adapter;



   /* @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView aluno = new TextView(this);

        aluno.setText("Vicente Leonardo");
        setContentView(aluno);
    }*/


    /*@Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> alunos = new ArrayList<>(Arrays.asList("Vicente Leo","Laise","Romario"));

        TextView primeiroAluno = findViewById(R.id.textView);
        TextView segundoAluno = findViewById(R.id.textView2);
        TextView terceiroAluno = findViewById(R.id.textView3);

        primeiroAluno.setText(alunos.get(0));
        segundoAluno.setText(alunos.get(1));
        terceiroAluno.setText(alunos.get(2));

    }*/


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        setTitle(TITULO_APP_BAR);

        configurarFabNovoAluno();
        configurarLista();





    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        CharSequence tituloMenu = item.getTitle();
        int itemMenu = item.getItemId();
//        if("Remover".equals(tituloMenu)){
        if(R.id.activity_lista_alunos_menu_remover == itemMenu){
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
            removeAluno(alunoEscolhido);
        }


        return super.onContextItemSelected(item);
    }


    private void configurarFabNovoAluno() {
        FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        botaoNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreFormularioEditarAluno();
            }
        });
    }

    private void abreFormularioEditarAluno() {
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaAlunos();


    }

    private void atualizaAlunos() {
        adapter.atualizaLista(alunoDao.buscarAlunos());
    }

    private void configurarLista() {
        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_listview);

        configuraAdapter(listaDeAlunos);

        configuraListenerClickPorItem(listaDeAlunos);

        registerForContextMenu(listaDeAlunos);
    }



    private void removeAluno(Aluno alunoSelecionado) {
        alunoDao.deletar(alunoSelecionado);
        adapter.remove(alunoSelecionado);
    }

    private void configuraListenerClickPorItem(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno aluno = (Aluno) parent.getItemAtPosition(position);
                Log.i("posicao", "onItemClick: clikilei no" + aluno);
                Toast.makeText(ListaAlunosActivity.this, "Clieck", Toast.LENGTH_SHORT).show();

                abreFormularioModoEditaAluno(aluno);
            }

            private void abreFormularioModoEditaAluno(Aluno aluno) {
                Intent vaiParaFormularioActivityEdicao = new Intent(new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class));
                vaiParaFormularioActivityEdicao.putExtra(ConstantesActvities.CHAVE_ALUNO, aluno);
                startActivity(vaiParaFormularioActivityEdicao);
            }
        });
    }

    private void configuraAdapter(ListView listaDeAlunos) {
        adapter = new ListaAlunosAdapter(this);
        listaDeAlunos.setAdapter(adapter);
    }
}
