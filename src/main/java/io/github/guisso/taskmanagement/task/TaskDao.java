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
import io.github.guisso.taskmanagement.repository.DbConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe TaskDao
 *
 * <code>
 * CREATE TABLE `tarefa` (
 * `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
 * `descricao` varchar(100) NOT NULL,
 * `progresso` tinyint(4) DEFAULT '0',
 * `conclusao` date DEFAULT NULL,
 * PRIMARY KEY (`id`),
 * UNIQUE KEY `id` (`id`)
 * ) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=latin1
 * </code>
 *
 * @author Luis Guisso &lt;luis dot guisso at ifnmg dot edu dot br&gt;
 * @version 0.1, 2022-10-24
 */
public class TaskDao extends Dao<Task> {
    
    public static final String TABLE = "tarefa";
    
    @Override
    public String getSaveStatment() {
        return "insert into " + TABLE + "(descricao, progresso, conclusao)  values (?, ?, ?)";
    }
    
    @Override
    public String getUpdateStatment() {
        return "update " + TABLE + " set descricao = ?, progresso = ?, conclusao = ? where id = ?";
    }
    
    @Override
    public void composeSaveOrUpdateStatement(PreparedStatement pstmt, Task e) {
        try {
            pstmt.setString(1, e.getDescription());
            // OR
            // pstmt.setObject(1, e.getDescription(), java.sql.Types.VARCHAR);

            // Null values
            // NOT! pstmt.setByte(2, e.getProgress());
            pstmt.setObject(2, e.getProgress(), java.sql.Types.TINYINT);

            // LocalDate
            pstmt.setObject(3, e.getConclusion(), java.sql.Types.DATE);

            // Just for the update
            if (e.getId() != null) {
                pstmt.setLong(4, e.getId());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TaskDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public String getFindByIdStatment() {
        return "select id, descricao, progresso, conclusao from tarefa where id = ?";
    }
    
    @Override
    public String getFindAllStatment() {
        return "select id, descricao, progresso, conclusao from tarefa";
    }
    
    @Override
    public Task extractObject(ResultSet resultSet) {
        
        Task task = null;
        
        try {
            task = new Task();
            task.setId(resultSet.getLong("id"));
            task.setDescription(resultSet.getString("descricao"));
            task.setProgress(resultSet.getByte("progresso"));
            task.setConclusion(
                    resultSet.getObject("conclusao", LocalDate.class));
        } catch (SQLException ex) {
            Logger.getLogger(TaskDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return task;
    }
    
    public List<Task> findByProgressLessThan20() {
        try ( PreparedStatement preparedStatement
                = DbConnection.getConnection().prepareStatement(
                        "select * from " + TABLE + " where progresso < 20")) {

            // Show the full sentence
            System.out.println(">> SQL: " + preparedStatement);

            // Performs the query on the database
            ResultSet resultSet = preparedStatement.executeQuery();

            // Returns the respective object
            return extractObjects(resultSet);
            
        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
        }
        
        return null;
    }
    
    public List<Task> findByDescription(String description) {
        try ( PreparedStatement preparedStatement
                = DbConnection.getConnection().prepareStatement(
                        "select * from " + TABLE
                        + " where descricao LIKE ?")) {
            
            preparedStatement.setString(1, "%" + description + "%");

            // Show the full sentence
            System.out.println(">> SQL: " + preparedStatement);

            // Performs the query on the database
            ResultSet resultSet = preparedStatement.executeQuery();

            // Returns the respective object
            return extractObjects(resultSet);
            
        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
        }
        
        return null;
    }
    
}
