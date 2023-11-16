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
package io.github.guisso.taskmanagement.task;

import io.github.guisso.taskmanagement.repository.Dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe SpecialTaskDao
 *
 * <code>
 * CREATE TABLE `tarefaespecial` (
 *  `id` bigint(20) unsigned NOT NULL,
 *  `especial` tinyint(1) DEFAULT 0,
 *  PRIMARY KEY (`id`),
 *  CONSTRAINT `tarefaespecial_ibfk_1` FOREIGN KEY (`id`) REFERENCES `tarefa` (`id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=latin1;
 * </code>
 *
 * @author Luis Guisso &lt;luis dot guisso at ifnmg dot edu dot br&gt;
 * @version 0.1, 2022-10-24
 */
public class SpecialTaskDao
        extends Dao<SpecialTask> {

    public static final String TABLE = "tarefaespecial";

    @Override
    public String getSaveStatment() {
        return "insert into " + TABLE + "(id, especial)"
                + " values (?, ?)";
    }

    @Override
    public String getUpdateStatment() {
        return "update " + TABLE
                + " set especial = ?"
                + " where id = ?";
    }

    @Override
    public Long saveOrUpdate(SpecialTask e) {

        // Save/update on Task table the task data
        Long idTask = new TaskDao().saveOrUpdate(e);

        if (e.getId() == null || e.getId() == 0) {
            // Negative ID act as flag to a new insertion
            // ATENTION to composeSaveOrUpdateStatement()
            e.setId(-idTask);

        } else {
            // Positive ID act as flag to update a record
            e.setId(idTask);
        }

        // Invoke the original method, otherwise an infity recursion will be started
        super.saveOrUpdate(e);

        return idTask;
    }

    @Override
    public void composeSaveOrUpdateStatement(PreparedStatement pstmt, SpecialTask e) {
        try {
            if (e.getId() != null && e.getId() < 0) {
                // INSERT
                pstmt.setLong(1, -e.getId()); // <<<<<< flag to force insertion
                pstmt.setBoolean(2, e.isSpecial());
            } else {
                // UPDATE
                pstmt.setBoolean(1, e.isSpecial());
                pstmt.setLong(2, e.getId());
            }

        } catch (SQLException ex) {
            Logger.getLogger(SpecialTaskDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getFindByIdStatment() {
        return "select t.id, descricao, progresso, conclusao, especial"
                + " from " + TABLE + " t"
                + " inner join " + TaskDao.TABLE + " te"
                + " on t.id = te.id"
                + " where t.id = ?";
    }

    @Override
    public String getFindAllStatment() {
        return "select id, descricao, progresso, conclusao, especial"
                + " from " + TABLE + " t"
                + " inner join " + TaskDao.TABLE + " te"
                + " on t.id = te.id"
                + " where exlcuido = false";
    }

    @Override
    public String getMoveToTrashStatement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getRestoreFromTrashStatement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getFindAllOnTrashStatement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public SpecialTask extractObject(ResultSet resultSet) {

        SpecialTask specialTask = null;

        try {
            specialTask = new SpecialTask();
            specialTask.setId(resultSet.getLong("id"));
            specialTask.setDescription(resultSet.getString("descricao"));
            specialTask.setProgress(resultSet.getByte("progresso"));
            specialTask.setConclusion(
                    resultSet.getObject("conclusao", LocalDate.class));
            specialTask.setSpecial(resultSet.getBoolean("especial"));
        } catch (SQLException ex) {
            Logger.getLogger(SpecialTaskDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return specialTask;
    }

    public List<SpecialTask> findByProgressLessThan20() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<SpecialTask> findByDescription(String description) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void moveToTrash(SpecialTask e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void restoreFromTrash(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<SpecialTask> findAllOnTrash() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
