import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { DialogConfirmationComponent } from 'src/app/core/dialog-confirmation/dialog-confirmation.component';
import { Pageable } from 'src/app/core/model/page/Pageable';
import { PrestamoEditComponent } from '../prestamo-edit/prestamo-edit.component';
import { PrestamoService } from '../prestamo.service';
import { Prestamo } from '../model/Prestamo';
import { ClientService } from 'src/app/client/client.service';
import { Client } from 'src/app/client/model/Client';
import { GameService } from 'src/app/game/game.service';
import { Game } from 'src/app/game/model/Game';


@Component({
selector: 'app-prestamo-list',
templateUrl: './prestamo-list.component.html',
styleUrls: ['./prestamo-list.component.scss']
})
export class PrestamoListComponent implements OnInit, AfterViewInit{

    @ViewChild(MatPaginator) paginator!: MatPaginator;


    clients : Client[];
    games: Game[];
    prestamos: Prestamo[];
    filterClient: Client;
    filterGame: Game;
    filterFecha: Date;

    pageNumber: number = 0;
    pageSize: number = 5;
    totalElements: number = 0;

    dataSource = new MatTableDataSource<Prestamo>();
    displayedColumns: string[] = ['id', 'game', 'client','fechaInicio', 'fechaFin', 'action'];

    constructor(
        private prestamoService: PrestamoService,
        private gameService: GameService,
        private clientService: ClientService,
        public dialog: MatDialog,
    ) { }

    ngOnInit(): void {
        
        this.loadPage();
        
        this.gameService.getGames().subscribe( 
            games => this.games = games
        );

        this.clientService.getClients().subscribe(
            clients => this.clients = clients
        );
        
    }

    
    ngAfterViewInit(){
        this.dataSource.paginator= this.paginator;
    }

    loadPage(event?: PageEvent) {

        let pageable : Pageable =  {
            pageNumber: this.pageNumber,
            pageSize: this.pageSize,
            sort: [{
                property: 'id',
                direction: 'ASC'
            }]
        }

        if (event != null) {
            pageable.pageSize = event.pageSize
            pageable.pageNumber = event.pageIndex;
        }

        let clientId = this.filterClient != null ? this.filterClient.id : null;
        let gameId = this.filterGame != null ? this.filterGame.id : null;
        let fechaInicio = this.filterFecha;

        if( clientId || gameId || fechaInicio){
            this.prestamoService.getPrestamo(clientId, gameId, fechaInicio).subscribe(
                prestamos => {this.prestamos = prestamos
                this.dataSource.data = prestamos;
                }
            );

        }else{

            this.prestamoService.getPrestamos(pageable).subscribe(data =>{
                this.dataSource.data = data.content;
                this.pageNumber = data.pageable.pageNumber;
                this.pageSize = data.pageable.pageSize;
                this.totalElements = data.totalElements;
        }); 
        }

    }  

    onCleanFilter(): void {
        this.filterClient = null;
        this.filterGame = null;
        this.filterFecha = null;

        this.onSearch();
    }

    onSearch(): void {

        this.loadPage();
       
    }
    createPrestamo() {      
        const dialogRef = this.dialog.open(PrestamoEditComponent, {
            data: {}
        });

        dialogRef.afterClosed().subscribe(result => {
            this.ngOnInit();
        });      
    }  

    editPrestamo(prestamo: Prestamo) {    
        const dialogRef = this.dialog.open(PrestamoEditComponent, {
            data: { prestamo: prestamo }
        });

        dialogRef.afterClosed().subscribe(result => {
            this.ngOnInit();
        });    
    }

    deletePrestamo(prestamo: Prestamo) {    
        const dialogRef = this.dialog.open(DialogConfirmationComponent, {
            data: { title: "Eliminar prestamo", description: "Atención si borra el prestamo se perderán sus datos.<br> ¿Desea eliminar el prestamo?" }
        });

        dialogRef.afterClosed().subscribe(result => {
            if (result) {
                this.prestamoService.deletePrestamo(prestamo.id).subscribe(result =>  {
                    this.ngOnInit();
                }); 
            }
        });
    }  
}
