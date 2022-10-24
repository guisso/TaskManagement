/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package io.github.guisso.taskmanagement;

import io.github.guisso.taskmanagemen.task.Task;
import io.github.guisso.taskmanagemen.task.TaskDao;
import io.github.guisso.taskmanagement.repository.Dao;
import java.time.LocalDate;

/**
 *
 * @author Luis Guisso &lt;luis dot guisso at ifnmg dot edu dot br&gt;
 */
public class TaskManagement {

    public static void main(String[] args) {
        // Criar um objeto
        Task limparCasa = new Task(null, "Limpar a casa", (byte) 20, null);
        System.out.println("> " + limparCasa);
        
        Task regarPlantas = new Task(null, "Regar plantas", null, null);
        System.out.println("> " + regarPlantas);
        
        Task revisarConteudo = new Task(null, "Revisar conteudo", (byte) 100, LocalDate.of(2022, 10, 15));
        System.out.println("> " + revisarConteudo);

        // Salvar no banco de dados
        new TaskDao().save(limparCasa);
        new TaskDao().save(regarPlantas);
        new TaskDao().save(revisarConteudo);
        
        // Recuperar objeto recém-salvo
        
        // Recuperar todos os objetos
        
    }
}
