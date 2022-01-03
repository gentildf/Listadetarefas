package com.dfgstudio.listadetarefas.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;

import com.dfgstudio.listadetarefas.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import com.dfgstudio.listadetarefas.adapter.TarefaAdapter;
import com.dfgstudio.listadetarefas.databinding.ActivityMainBinding;
import com.dfgstudio.listadetarefas.helper.DbHelper;
import com.dfgstudio.listadetarefas.helper.RecyclerItemClickListener;
import com.dfgstudio.listadetarefas.model.Tarefa;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private RecyclerView recyclerView;
    private TarefaAdapter tarefaAdapter;
    private List<Tarefa> listaTarefas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        // Instaciar recycler
        recyclerView = findViewById(R.id.recyclerView);

//        //Instanciar banco
//        DbHelper db = new DbHelper(getApplicationContext());
//
//        ContentValues cv = new ContentValues(); // ContentValues permite definir itens como se fosse um array;
//        cv.put("nome", "Teste");
//
//        db.getWritableDatabase().insert("tarefas", null, cv);


        //Eventos de clique em um RecyclerView


        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                //adicao
                                Log.i("clique", "Clique curto");

                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                //delecao
                                Log.i("clique", "Clique longo");

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdicionarTarefaMainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void carregarListaTarefas(){

        //Lista de tarefas
        Tarefa tarefa1 = new Tarefa();
        tarefa1.setNomeTarefa("Tarefa 1");
        listaTarefas.add(tarefa1);

        Tarefa tarefa2 = new Tarefa();
        tarefa2.setNomeTarefa("Tarefa 2");
        listaTarefas.add(tarefa2);


        // Configurar adapter
        tarefaAdapter = new TarefaAdapter( listaTarefas );

        //Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(tarefaAdapter);
    }

    //Utilizar o onStart para chamar o metodo carregarlista porque toda vez que voltar para tela carregara a lista.
    @Override
    protected void onStart() {
        carregarListaTarefas();
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_adicionar_tarefa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}