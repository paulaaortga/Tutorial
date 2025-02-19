package com.ccsw.tutorial.prestamo.model;

import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.game.model.Game;
import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * @author ccsw
 *
 */
@Entity
@Table(name = "prestamo")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(name = "FECHAINICIO", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "FECHAFIN", nullable = false)
    private LocalDate fechaFin;

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
     * @return category
     */
    public Game getGame() {

        return this.game;
    }

    /**
     * @param game new value of {@link #getGame}.
     */
    public void setGame(Game game) {

        this.game = game;
    }

    /**
     * @return client
     */
    public Client getClient() {

        return this.client;
    }

    /**
     * @param client new value of {@link #getClient}.
     */
    public void setClient(Client client) {

        this.client = client;
    }

    /**
     * @return title
     */
    public LocalDate getFechaInicio() {

        return this.fechaInicio;
    }

    /**
     * @param fechaInicio new value of {@link #getFechaInicio()}
     */
    public void setFechaInicio(LocalDate fechaInicio) {

        this.fechaInicio = fechaInicio;
    }

    /**
     * @return fechaFin
     */
    public LocalDate getFechaFin() {

        return this.fechaFin;
    }

    /**
     * @param fechaFin new value of {@link #getFechaFin()}
     */
    public void setFechaFin(LocalDate fechaFin) {

        this.fechaFin = fechaFin;
    }

}