package com.dfgstudio.listadetarefas.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.dfgstudio.listadetarefas.R;
import com.dfgstudio.listadetarefas.helper.TarefaDAO;
import com.dfgstudio.listadetarefas.model.Tarefa;
import com.google.android.material.textfield.TextInputEditText;

public class AdicionarTarefaMainActivity extends AppCompatActivity {

    private TextInputEditText editTarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa_main);

        editTarefa = findViewById(R.id.textTarefa);


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

                if( !nomeTarefa.isEmpty() ){
                    Tarefa tarefa = new Tarefa();
                    tarefa.setNomeTarefa(nomeTarefa);
                    tarefaDAO.salvar(tarefa); // Recebe texto para salvar
                    finish(); // Encerrar activity
                }

                break;

        }

        return super.onOptionsItemSelected(item);
    }
}