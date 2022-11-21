package br.com.vicente.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.com.vicente.R;
import br.com.vicente.dao.AlunoDAO;
import br.com.vicente.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_NOVO_ALUNO = "Novo aluno";
    private static final String TITULO_APPBAR_EDITA_ALUNO = "Editar aluno";
    private EditText nome;
    private EditText telefone;
    private EditText email;
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(br.com.vicente.R.layout.activity_formulario_aluno);


        inicializacaoDosCampos();
//        configurarBotaoSalvar(); depois que criou o menu la em cima nao precisa mais do botao

        carregaAluno();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_aluno_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.activity_formulario_aluno_menu_salvar){
            finalizarFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregaAluno() {
        Intent dados = getIntent();
        if(dados.hasExtra(ConstantesActvities.CHAVE_ALUNO)){
            aluno = (Aluno) dados.getSerializableExtra(ConstantesActvities.CHAVE_ALUNO);
            setTitle(TITULO_APPBAR_EDITA_ALUNO);
            preencherCampos();
        }
        else{
            setTitle(TITULO_APPBAR_NOVO_ALUNO);
            aluno = new Aluno();
        }

    }

    private void preencherCampos() {
        nome.setText(aluno.getNomeAluno());
        telefone.setText(aluno.getTelefoneAluno());
        email.setText(aluno.getEmailAluno());
    }

//    depois que criou o menu la em cima nao precisa mais do botao
//    private void configurarBotaoSalvar() {
//        Button botaoSalvar = findViewById(R.id.acitivity_formulario_aluno_botao_salvar);
//
//
//        botaoSalvar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                /*Aluno alunoCriado = preencheAluno();
//                salvar(alunoCriado);*/
//
//                finalizarFormulario();
//
//            }
//        });
//    }

    private void finalizarFormulario() {
        preencheAluno();
        if(aluno.temIdValido()){
            alunoDAO.editar(aluno);
        }
        else{
            alunoDAO.salvar(aluno);
        }

        finish();
    }

    private void inicializacaoDosCampos() {
        nome = findViewById(R.id.activity_formulario_aluno_nome);
        telefone = findViewById(R.id.activity_formulario_aluno_telefone);
        email = findViewById(R.id.activity_formulario_aluno_email);
    }



    @NonNull
    private void preencheAluno() {
        aluno.setNomeAluno(nome.getText().toString());
        aluno.setTelefoneAluno(telefone.getText().toString());
        aluno.setEmailAluno(email.getText().toString());

        /*Aluno alunoCriado = new Aluno(nomeAluno, telefoneAluno, emailAluno);*/

    }
}