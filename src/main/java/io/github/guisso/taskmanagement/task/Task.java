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

import io.github.guisso.taskmanagement.entity.Entity;
import java.time.LocalDate;

/**
 * Task class
 *
 * @author Luis Guisso &lt;luis dot guisso at ifnmg dot edu dot br&gt;
 * @version 0.2, 2024-08-29
 */
public class Task
        extends Entity {

    private String description;
    private Byte progress;
    private LocalDate conclusion;
    private Boolean concluded;

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Task() {
    }

    public Task(Long id, String description, Byte progress, LocalDate conclusion) {
        setId(id);

//        this.description = description;
        setDescription(description);  // Security problem!

//        this.progress = progress;
        setProgress(progress);  // Security problem!

//        this.conclusion = conclusion;
        setConclusion(conclusion);  // Security problem!
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getDescription() {
        return description;
    }

    public final void setDescription(String description) {
        if (description == null) {
            throw new IllegalArgumentException("Null description");
        } else if (description.length() > 100) {
            throw new IllegalArgumentException("The description exceeds 100 characters");
        }

        this.description = description;
    }

    public Byte getProgress() {
        return progress;
    }

    public final void setProgress(Byte progress) {
        if (progress != null
                && (progress < 0 || progress > 100)) {
            throw new IllegalArgumentException("Progress must be between 0 and 100");
        } else {
            concluded = progress != null && progress == 100;
        }

        this.progress = progress;
    }

    public LocalDate getConclusion() {
        return conclusion;
    }

    public final void setConclusion(LocalDate conclusion) {

        if (conclusion == null) {
            this.conclusion = null;
            this.concluded = false;
            return;
        }

        if (conclusion.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Conclusion must be before now");
        }

        concluded = true;
        progress = (byte) 100;

        this.conclusion = conclusion;
    }

    public Boolean getConcluded() {
        return concluded;
    }
    //</editor-fold>

    @Override
    public String toString() {
        return "Task{"
                + "id=" + getId()
                + ", description=" + description
                + ", progress=" + progress
                + ", conclusion=" + conclusion
                + ", concluded=" + concluded
                + '}';
    }

}
