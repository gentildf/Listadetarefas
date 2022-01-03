package com.dfgstudio.listadetarefas.model;

import java.io.Serializable;

public class Tarefa implements Serializable {

    private Long id;
    private String nomeTarefa;

    // Getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {

        this.id = id;
    }
    public String getNomeTarefa() {

        return nomeTarefa;
    }
    public void setNomeTarefa(String nomeTarefa) {

        this.nomeTarefa = nomeTarefa;
    }
    // -------------------------------------
}
