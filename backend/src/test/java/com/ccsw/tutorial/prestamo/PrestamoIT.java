package com.ccsw.tutorial.prestamo;

import com.ccsw.tutorial.common.pagination.PageableRequest;
import com.ccsw.tutorial.config.ResponsePage;
import com.ccsw.tutorial.game.model.GameDto;
import com.ccsw.tutorial.prestamo.model.PrestamoDto;
import com.ccsw.tutorial.prestamo.model.PrestamoSearchDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PrestamoIT {

    public static final String LOCALHOST = "http://localhost:";
    public static final String SERVICE_PATH = "/prestamo";

    public static final Long DELETE_PRESTAMO_ID = 6L;
    public static final Long MODIFY_PRESTAMO_ID = 3L;
    public static final String NEW_PRESTAMO_FECHAINICIO = "2025-01-20";
    public static final String NEW_PRESTAMO_FECHAFIN = "2025-01-22";

    private static final int TOTAL_PRESTAMOS = 6;
    private static final int PAGE_SIZE = 3;
    private static final int PRESTAMOS = 7;

    private static final String CLIENT_ID_PARAM = "idClient";
    private static final String GAME_ID_PARAM = "idGame";
    private static final String FECHA_PARAM = "Fecha";

    private static final Long EXISTS_CLIENT = 1L;
    private static final Long EXISTS_GAME = 1L;
    private static final String EXISTS_FECHA = "2025-10-03";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    ParameterizedTypeReference<ResponsePage<PrestamoDto>> responseTypePage = new ParameterizedTypeReference<ResponsePage<PrestamoDto>>() {
    };

    ParameterizedTypeReference<List<GameDto>> responseType = new ParameterizedTypeReference<List<GameDto>>() {
    };

    private String getUrlWithParams() {
        return UriComponentsBuilder.fromHttpUrl(LOCALHOST + port + SERVICE_PATH).queryParam(CLIENT_ID_PARAM, "{" + CLIENT_ID_PARAM + "}").queryParam(GAME_ID_PARAM, "{" + GAME_ID_PARAM + "}").queryParam(FECHA_PARAM, "{" + FECHA_PARAM + "}")
                .encode().toUriString();
    }

    @Test
    public void findFirstPageWithFiveSizeShouldReturnFirstFiveResults() {

        PrestamoSearchDto searchDto = new PrestamoSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));

        ResponseEntity<ResponsePage<PrestamoDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(TOTAL_PRESTAMOS, response.getBody().getTotalElements());
        assertEquals(PAGE_SIZE, response.getBody().getContent().size());
    }

    @Test
    public void findSecondPageWithFiveSizeShouldReturnLastResult() {

        int elementsCount = TOTAL_PRESTAMOS - PAGE_SIZE;

        PrestamoSearchDto searchDto = new PrestamoSearchDto();
        searchDto.setPageable(new PageableRequest(1, PAGE_SIZE));

        ResponseEntity<ResponsePage<PrestamoDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(TOTAL_PRESTAMOS, response.getBody().getTotalElements());
        assertEquals(elementsCount, response.getBody().getContent().size());
    }

    @Test
    public void findExistsGAMEShouldReturnGames() {

        int GAMES_WITH_FILTER = 3;

        Map<String, Object> params = new HashMap<>();
        params.put(CLIENT_ID_PARAM, null);
        params.put(GAME_ID_PARAM, EXISTS_GAME);
        params.put(FECHA_PARAM, null);

        ResponseEntity<List<GameDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null, responseType, params);

        assertNotNull(response);
        assertEquals(GAMES_WITH_FILTER, response.getBody().size());
    }

    @Test
    public void findExistsCLIENTShouldReturnGames() {

        int CLIENTS_WITH_FILTER = 2;

        Map<String, Object> params = new HashMap<>();
        params.put(CLIENT_ID_PARAM, EXISTS_CLIENT);
        params.put(GAME_ID_PARAM, null);
        params.put(FECHA_PARAM, null);

        ResponseEntity<List<GameDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null, responseType, params);

        assertNotNull(response);
        assertEquals(CLIENTS_WITH_FILTER, response.getBody().size());
    }

    @Test
    public void findExistsFECHAShouldReturnGames() {

        int FECHA_WITH_FILTER = 6;

        Map<String, Object> params = new HashMap<>();
        params.put(CLIENT_ID_PARAM, null);
        params.put(GAME_ID_PARAM, null);
        params.put(FECHA_PARAM, EXISTS_FECHA);

        ResponseEntity<List<GameDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null, responseType, params);

        assertNotNull(response);
        assertEquals(FECHA_WITH_FILTER, response.getBody().size());
    }

    @Test
    public void findExistsALLhouldReturnGames() {

        int FECHA_WITH_FILTER = 1;
        int CLIENT_WITH_FILTER = 1;
        int GAME_WITH_FILTER = 1;

        Map<String, Object> params = new HashMap<>();
        params.put(CLIENT_ID_PARAM, EXISTS_CLIENT);
        params.put(GAME_ID_PARAM, EXISTS_GAME);
        params.put(FECHA_PARAM, EXISTS_FECHA);

        ResponseEntity<List<GameDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null, responseType, params);

        assertNotNull(response);
        assertEquals(FECHA_WITH_FILTER, response.getBody().size());
        assertEquals(CLIENT_WITH_FILTER, response.getBody().size());
        assertEquals(GAME_WITH_FILTER, response.getBody().size());
    }

    /*
        @Test
        public void saveWithoutIdShouldCreateNewAuthor() {

            long newAuthorId = TOTAL_PRESTAMOS + 1;
            long newAuthorSize = TOTAL_PRESTAMOS + 1;

            PrestamoDto dto = new PrestamoDto();
            dto.setFechaInicio(NEW_PRESTAMO_FECHAFIN);
            dto.setFechaFin(NEW_PRESTAMO_FECHAFIN);

            restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

            AuthorSearchDto searchDto = new AuthorSearchDto();
            searchDto.setPageable(new PageableRequest(0, (int) newAuthorSize));

            ResponseEntity<ResponsePage<AuthorDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

            assertNotNull(response);
            assertEquals(newAuthorSize, response.getBody().getTotalElements());

            AuthorDto author = response.getBody().getContent().stream().filter(item -> item.getId().equals(newAuthorId)).findFirst().orElse(null);
            assertNotNull(author);
            assertEquals(NEW_AUTHOR_NAME, author.getName());
        }
    */
    @Test
    public void deleteWithExistsIdShouldDeletePrestamo() {

        long newPrestamosSize = TOTAL_PRESTAMOS - 1;

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "/" + DELETE_PRESTAMO_ID, HttpMethod.DELETE, null, Void.class);

        PrestamoSearchDto searchDto = new PrestamoSearchDto();
        searchDto.setPageable(new PageableRequest(0, TOTAL_PRESTAMOS));

        ResponseEntity<ResponsePage<PrestamoDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(newPrestamosSize, response.getBody().getTotalElements());
    }

    @Test
    public void deleteWithNotExistsIdShouldThrowException() {

        long deletePrestamoId = PRESTAMOS + 1;

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "/" + deletePrestamoId, HttpMethod.DELETE, null, Void.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

}