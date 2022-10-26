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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class DbConnection
 *
 * @author Luis Guisso &lt;luis dot guisso at ifnmg dot edu dot br&gt;
 * @version 0.1, 2022-10-24
 */
public class DbConnection {

    // Retains the established connection to the database during system operation
    private static Connection connection;

    /**
     * URL database connection
     * (protocol/sgbd/ip/port/database/parameters)
     */
    public static final String URL;

    // Database user
    private static final String USER;

    // Database password
    private static final String PASSWORD;

    // Static initialization
    static {
        // Local server
        URL = "jdbc:mysql://127.0.0.1:3306/" + Dao.DB
                + "?useUnicode=true"
                + "&useJDBCCompliantTimezoneShift=true"
                + "&serverTimezone=UTC"
                + "&autoReconnect=true";
        USER = "root";
        PASSWORD = "";
    }

    //<editor-fold defaultstate="collapsed" desc="Construtor privado">
    /*
     * Private constructor to force access to the connection by the 
     * static member getConnection() without requiring the generation of 
     * new objects ConexaoBd ConexaoBd
     */
    private DbConnection() {
    }
    //</editor-fold>

    /**
     * Establishes and generates database connection retention
     *
     * @return Dabatase connection
     */
    public static Connection getConnection() {

        // If there is no connection established...
        if (connection == null) {
            // ... try ...
            try {
                System.out.println(">> New database connection");
                // ... establish and retain the connection 
                //     from the provided URL, username and password
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException ex) {
                // TODO Rever procedimento e encerrar o programa em caso de falha
                // Log failure
                Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
                // System.exit(-1);
            }
        }

        // Returns the established connection
        return connection;
    }
}
