package com.dfgstudio.listadetarefas.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.dfgstudio.listadetarefas.R;
import com.dfgstudio.listadetarefas.helper.TarefaDAO;
import com.dfgstudio.listadetarefas.model.Tarefa;
import com.google.android.material.textfield.TextInputEditText;

public class AdicionarTarefaMainActivity extends AppCompatActivity {

    private TextInputEditText editTarefa;
    private Tarefa tarefaAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa_main);

        editTarefa = findViewById(R.id.textTarefa);

        //Recuperar tarefa para edicao
        tarefaAtual = (Tarefa) getIntent().getSerializableExtra("tarefaSelecionada");

        //Configurar tarefa na caixa de texto.
        if(tarefaAtual != null){
            editTarefa.setText(tarefaAtual.getNomeTarefa());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.menu_adicionar_tarefa, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch ( item.getItemId() ){
            case R.id.itemSalvar:
                // Açao para salvar o item
                TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
                String nomeTarefa = editTarefa.getText().toString();

                if (tarefaAtual != null){ //edicao

                    if( !nomeTarefa.isEmpty() ){
                        Tarefa tarefa = new Tarefa();
                        tarefa.setNomeTarefa( nomeTarefa );
                        tarefa.setId(tarefaAtual.getId());

                        // atualizar banco
                        if( tarefaDAO.atualizar(tarefa) ){
                            finish(); // Encerrar activity
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Tarefa atualizada!",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }else{
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Erro ao tentar atualizar tarefa!",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }

                    }

                } else { //salvar
                    if( !nomeTarefa.isEmpty() ){
                        Tarefa tarefa = new Tarefa();
                        tarefa.setNomeTarefa(nomeTarefa);
                        if (tarefaDAO.salvar(tarefa)){
                            finish(); // Encerrar activity
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Tarefa salva!",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }else{
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Erro ao salvar tarefa!",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }

                    }
                }
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}