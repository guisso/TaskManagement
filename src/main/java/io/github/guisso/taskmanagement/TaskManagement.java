/*
 * CC BY-NC-SA 4.0
 *
 * Copyright 2022 Luis Guisso &lt;luis dot guisso at ifnmg dot edu dot br&gt;.
 *
 * Attribution-NonCommercial-ShareAlike 4.0 International (CC BY-NC-SA 4.0)
 *
 * You are free to:
 *   Share - copy and redistribute the material in any medium or format
 *   Adapt - remix, transform, and build upon the material
 *
 * Under the following terms:
 *   Attribution - You must give appropriate credit, provide 
 *   a link to the license, and indicate if changes were made.
 *   You may do so in any reasonable manner, but not in any 
 *   way that suggests the licensor endorses you or your use.
 *   NonCommercial - You may not use the material for commercial purposes.
 *   ShareAlike - If you remix, transform, or build upon the 
 *   material, you must distribute your contributions under 
 *   the same license as the original.
 *   No additional restrictions - You may not apply legal 
 *   terms or technological measures that legally restrict 
 *   others from doing anything the license permits.
 *
 * Notices:
 *   You do not have to comply with the license for elements 
 *   of the material in the public domain or where your use 
 *   is permitted by an applicable exception or limitation.
 *   No warranties are given. The license may not give you 
 *   all of the permissions necessary for your intended use. 
 *   For example, other rights such as publicity, privacy, 
 *   or moral rights may limit how you use the material.
 */
package io.github.guisso.taskmanagement;

import io.github.guisso.taskmanagement.task.Task;
import io.github.guisso.taskmanagement.task.TaskDao;
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
        Task limparCasaAux = new TaskDao().findById(limparCasaId);
        System.out.println("> " + limparCasaAux);
        
        // Atualizar registro já salvo
        limparCasaAux.setDescription(limparCasaAux.getDescription() + "!!!");
        new TaskDao().saveOrUpdate(limparCasaAux);

        // Recuperar todos os objetos
        List<Task> tasks = new TaskDao().findAll();
        System.out.println(">> " + tasks);
    }
}
