package com.ccsw.tutorial.prestamo.model;

import com.ccsw.tutorial.client.model.ClientDto;
import com.ccsw.tutorial.game.model.GameDto;

import java.time.LocalDate;

/**
 * @author ccsw
 *
 */
public class PrestamoDto {

    private Long id;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    private GameDto game;

    private ClientDto client;

    /**
     * @return id
     */
    public Long getId() {

        return this.id;
    }

    /**
     * @param id new value of {@link #getId}.
     */
    public void setId(Long id) {

        this.id = id;
    }

    /**
     * @return game
     */
    public GameDto getGame() {

        return this.game;
    }

    /**
     * @param game new value of {@link #getGame}.
     */
    public void setGame(GameDto game) {

        this.game = game;
    }

    /**
     * @return client
     */
    public ClientDto getClient() {

        return this.client;
    }

    /**
     * @param client new value of {@link #getClient}.
     */
    public void setClient(ClientDto client) {

        this.client = client;
    }

    /**
     * @return fechaInicio
     */
    public LocalDate getFechaInicio() {

        return this.fechaInicio;
    }

    /**
     * @param fechaInicio new value of {@link #getFechaInicio()}.
     */
    public void setFechaInicio(LocalDate fechaInicio) {

        this.fechaInicio = fechaInicio;
    }

    /**
     * @return fechaInicio
     */
    public LocalDate getFechaFin() {

        return this.fechaFin;
    }

    /**
     * @param fechaFin new value of {@link #getFechaFin()}.
     */
    public void setFechaFin(LocalDate fechaFin) {

        this.fechaFin = fechaFin;
    }

}