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
package io.github.guisso.taskmanagement.repository;

import io.github.guisso.taskmanagement.entity.Entity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * Interface IDao
 *
 * @author Luis Guisso &lt;luis dot guisso at ifnmg dot edu dot br&gt;
 * @version 0.1, 2022-10-24
 * @param <T> Data type
 */
public interface IDao<T extends Entity> {

    // Save
    public String getSaveStatment();

    public String getUpdateStatment();

    public void composeSaveOrUpdateStatement(PreparedStatement pstmt, T e);

    public Long saveOrUpdate(T e);

    // Get by ID
    public String getFindByIdStatment();

    public T findById(Long id);

    // Get all
    public String getFindAllStatment();

    public List<T> findAll();

    // Trash
    public String getMoveToTrashStatement();
    public void moveToTrash(T e);

    public String getRestoreFromTrashStatement();
    public void restoreFromTrash(Long id); // OU T e

    public String getFindAllOnTrashStatement();
    public List<T> findAllOnTrash();

    // Assembly objects
    public T extractObject(ResultSet resultSet);

    public List<T> extractObjects(ResultSet resultSet);

}
