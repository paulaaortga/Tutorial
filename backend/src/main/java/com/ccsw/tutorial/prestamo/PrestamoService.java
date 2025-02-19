package com.ccsw.tutorial.prestamo;

import com.ccsw.tutorial.prestamo.model.Prestamo;
import com.ccsw.tutorial.prestamo.model.PrestamoDto;
import com.ccsw.tutorial.prestamo.model.PrestamoSearchDto;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

/**
 * @author ccsw
 *
 */
public interface PrestamoService {

    /**
     * Recupera los juegos filtrando opcionalmente por título y/o categoría
     *
     * @param idClient PK del cliente
     * @param idGame PK del juego
     * @return {@link List} de {@link Prestamo}
     */
    List<Prestamo> find(Long idClient, Long idGame, LocalDate fechaInicio);

    /**
     * Método para recuperar un listado paginado de {@link Prestamo}
     *
     * @param dto dto de búsqueda
     * @return {@link Page} de {@link Prestamo}
     */
    Page<Prestamo> findPage(PrestamoSearchDto dto);

    /**
     * Método para crear  un {@link Prestamo}
     *
     * @param id PK de la entidad
     * @param dto datos de la entidad
     */
    void save(Long id, PrestamoDto dto);

    /**
     * Método para eliminar un {@link Prestamo}
     *
     * @param id PK de la entidad
     */
    void delete(Long id) throws Exception;

}