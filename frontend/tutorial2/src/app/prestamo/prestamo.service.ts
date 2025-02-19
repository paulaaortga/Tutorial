import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Pageable } from '../core/model/page/Pageable';
import { Prestamo } from './model/Prestamo';
import { PrestamoPage } from './model/PrestamoPage';


@Injectable({
  providedIn: 'root'
})
export class PrestamoService {

  constructor(
    private http: HttpClient
  ) { }

  getPrestamos(pageable: Pageable): Observable<PrestamoPage> {

    return this.http.post<PrestamoPage>('http://localhost:8080/prestamo', {pageable:pageable});
}

getPrestamo(clientId?: number, gameId?: number, fechaInicio?: Date): Observable<Prestamo[]> {            
  return this.http.get<Prestamo[]>(this.composeFindUrl(clientId, gameId, fechaInicio));
}

savePrestamo(prestamo: Prestamo): Observable<void> {
  let url = 'http://localhost:8080/prestamo';
        if (prestamo.id != null) url += '/'+prestamo.id;

        return this.http.put<void>(url, prestamo);
}

deletePrestamo(idPrestamo : number): Observable<void> {
  return this.http.delete<void>('http://localhost:8080/prestamo/'+idPrestamo);
} 

private composeFindUrl(clientId?: number, gameId?: number, fechaInicio?: Date) : string {
  let params = '';

  if (clientId != null) {
    if (params != '') params += "&";
    params += "idClient="+clientId;
}

  if (gameId != null) {
      if (params != '') params += "&";
      params += "idGame="+gameId;
  }

  if (fechaInicio != null) {
    let fechaFormato = fechaInicio.toISOString().split('T')[0];
    params += 'fechaInicio='+fechaFormato;
}

  let url = 'http://localhost:8080/prestamo'

  if (params == '') return url;
  else return url + '?' +params;
}

}
