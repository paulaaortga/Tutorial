import { PrestamoPage } from "./PrestamoPage";

export const PRESTAMO_DATA: PrestamoPage = {
    content: [
        { id: 1, fechaInicio: new Date(2025-1-20), fechaFin: new Date(2025-2-1), game: { id: 1, title: 'Juego 1', age: 6, category: { id: 1, name: 'Categoría 1' }, author: { id: 1, name: 'Autor 1', nationality: 'Nacionalidad 1' } }, client:{id:1, name:'Paula' } },
        { id: 2, fechaInicio: new Date(2025-1-20), fechaFin: new Date(2025-2-2), game: { id: 2, title: 'Juego 2', age: 6, category: { id: 1, name: 'Categoría 1' }, author: { id: 1, name: 'Autor 1', nationality: 'Nacionalidad 1' } }, client:{id:2, name:'Pedro' } },
        { id: 3, fechaInicio: new Date(2025-11-23), fechaFin: new Date(2025-11-25), game: { id: 1, title: 'Juego 1', age: 6, category: { id: 1, name: 'Categoría 1' }, author: { id: 1, name: 'Autor 1', nationality: 'Nacionalidad 1' } }, client:{id:1, name:'Paula' } },
        { id: 4, fechaInicio: new Date(2025-12-24), fechaFin: new Date(2025-12-31), game: { id: 1, title: 'Juego 1', age: 6, category: { id: 1, name: 'Categoría 1' }, author: { id: 1, name: 'Autor 1', nationality: 'Nacionalidad 1' } }, client:{id:3, name:'Rosa' } },
        { id: 5, fechaInicio: new Date(2025-1-16), fechaFin: new Date(2025-2-1), game: { id: 1, title: 'Juego 1', age: 6, category: { id: 1, name: 'Categoría 1' }, author: { id: 1, name: 'Autor 1', nationality: 'Nacionalidad 1' } }, client:{id:1, name:'Paula' } },
        { id: 6, fechaInicio: new Date(2025-7-5), fechaFin: new Date(2025-7-6), game: { id: 1, title: 'Juego 1', age: 6, category: { id: 1, name: 'Categoría 1' }, author: { id: 1, name: 'Autor 1', nationality: 'Nacionalidad 1' } }, client:{id:1, name:'Paula' } },
        { id: 7, fechaInicio: new Date(2025-3-10), fechaFin: new Date(2025-3-12), game: { id: 1, title: 'Juego 1', age: 6, category: { id: 1, name: 'Categoría 1' }, author: { id: 1, name: 'Autor 1', nationality: 'Nacionalidad 1' } }, client:{id:1, name:'Paula' } },

    ],  
    pageable : {
        pageSize: 5,
        pageNumber: 0,
        sort: [
            {property: "id", direction: "ASC"}
        ]
    },
    totalElements: 7
}