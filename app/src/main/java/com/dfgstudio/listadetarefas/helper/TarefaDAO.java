package com.dfgstudio.listadetarefas.helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dfgstudio.listadetarefas.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

// Classe responsavel para salvar os dados no banco de dados (nao vai ser o DBhelper
public class TarefaDAO implements ITarefaDAO {

    private SQLiteDatabase escreve, le;


    public TarefaDAO(Context context) {
        DbHelper db = new DbHelper(context);
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();

    }

    @Override
    public boolean salvar(Tarefa tarefa) {
        ContentValues cv = new ContentValues();
        cv.put("nome", tarefa.getNomeTarefa());

        try{
            escreve.insert(DbHelper.TABELA_TAREFAS, null, cv);
        }catch (Exception e){
            Log.e("INFO", "Erro ao salvar tarefa" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean atualizar(Tarefa tarefa) {
        return false;
    }

    @Override
    public boolean deletar(Tarefa tarefa) {
        return false;
    }

    @Override
    public List<Tarefa> listar() {

        List<Tarefa> tarefas = new ArrayList<>();

        String sql = "SELECT * FROM " + DbHelper.TABELA_TAREFAS + " ;";
        Cursor c = le.rawQuery(sql, null);

        while (c.moveToNext()){

            Tarefa tarefa = new Tarefa();

            @SuppressLint("Range") Long id = c.getLong( c.getColumnIndex("id") );
            @SuppressLint("Range") String nomeTarefa = c.getString( c.getColumnIndex("nomeTarefa") );


            tarefa.setId(id);
            tarefa.setNomeTarefa(nomeTarefa);

            tarefas.add(tarefa);

        }

        return tarefas;
    }
}
