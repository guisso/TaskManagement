/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package io.github.guisso.taskmanagement;

import io.github.guisso.taskmanagemen.task.Task;
import io.github.guisso.taskmanagemen.task.TaskDao;
import java.time.LocalDate;
import java.util.List;

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
        Long limparCasaId = new TaskDao().saveOrUpdate(limparCasa);
        limparCasa.setId(limparCasaId);
        
        Long regarPlantasId = new TaskDao().saveOrUpdate(regarPlantas);
        regarPlantas.setId(regarPlantasId);
        
        Long revisarConteudoId = new TaskDao().saveOrUpdate(revisarConteudo);
        revisarConteudo.setId(revisarConteudoId);
        
        // Recuperar objeto recém-salvo
        Task limparCasaAux = new TaskDao().findById(revisarConteudoId);
        System.out.println("> " + limparCasaAux);
        
        // Recuperar todos os objetos
        List<Task> tasks = new TaskDao().findAll();
        System.out.println(">> " + tasks);
    }
}
