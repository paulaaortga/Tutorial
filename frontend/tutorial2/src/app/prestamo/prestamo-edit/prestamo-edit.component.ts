import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ClientService } from 'src/app/client/client.service';
import { Client } from 'src/app/client/model/Client';
import { GameService } from 'src/app/game/game.service';
import { Game } from 'src/app/game/model/Game';
import { PrestamoService } from '../prestamo.service';
import { Prestamo } from '../model/Prestamo';
import { FormGroup, FormBuilder, Validators, AbstractControl, ValidationErrors } from '@angular/forms';

@Component({
selector: 'app-prestamo-edit',
templateUrl: './prestamo-edit.component.html',
styleUrls: ['./prestamo-edit.component.scss']
})
export class PrestamoEditComponent implements OnInit {

    prestamo : Prestamo;
    games: Game[];
    clients: Client[];

    prestamoForm!: FormGroup;

    diasDiferencia: number | null = null; 

    constructor(
        private fb: FormBuilder,
        public dialogRef: MatDialogRef<PrestamoEditComponent>,
        @Inject(MAT_DIALOG_DATA) public data: any,
        private prestamoService: PrestamoService,
        private gameService: GameService,
        private clientService: ClientService,
    ) { }

    ngOnInit(): void {
        if (this.data.prestamo != null) {
            this.prestamo = Object.assign({}, this.data.prestamo);
        }
        else {
            this.prestamo = new Prestamo();
        }

        this.gameService.getGames().subscribe(
          games => {
              this.games = games;

              if (this.prestamo.game != null) {
                  let gameFilter: Game[] = games.filter(game => game.id == this.data.prestamo.game.id);
                  if (gameFilter != null) {
                      this.prestamo.game = gameFilter[0];
                  }
              }
          }
      );

      this.clientService.getClients().subscribe(
          clients => {
              this.clients = clients

              if (this.prestamo.client != null) {
                  let clientFilter: Client[] = clients.filter(client => client.id == this.data.prestamo.client.id);
                  if (clientFilter != null) {
                      this.prestamo.client = clientFilter[0];
                  }
              }
          }
      );

      this.prestamoForm = this.fb.group({
        fechaInicio: ['', Validators.required],
        fechaFin: ['', Validators.required, this.validateFecha.bind(this), this.validateFechaMaxima.bind(this)]
      });
/*
    this.prestamoForm.get("fechaInicio").valueChanges.subscribe(vFecha => {
            const fechaFin= this.prestamoForm.get("fechaFin").value;

            if (vFecha && fechaFin){
                const error = this.validateFecha(this.prestamoForm);
                this.prestamoForm.get("fechaFin").setErrors(error);
            }
    });
*/
    }

    validateFecha() {
        let fechaInicio= this.prestamo.fechaInicio;
        let fechaFin= this.prestamo.fechaFin

        if (fechaFin < fechaInicio){
            return {fechaInvalida: true};
        }
        return null;
    }

    validateFechaMaxima(){
        let fechaInicio= this.prestamo.fechaInicio;
        let fechaFin= this.prestamo.fechaFin;

        if (fechaInicio && fechaFin){
            let inicio= new Date(fechaInicio);
            let fin = new Date(fechaFin);

            let diferencia = fin.getTime() - inicio.getTime();
            let diferenciaDias = diferencia / (1000*60*60*24);
            this.diasDiferencia = diferenciaDias;
            
            if (diferenciaDias > 14){
                return {fechaMaxima: true};
            }
        }
        
    }

    onSave() {
        if(this.prestamo && !this.validateFecha() && !this.validateFechaMaxima()){
        this.prestamoService.savePrestamo(this.prestamo).subscribe(result =>  {
            this.dialogRef.close();
        }); 
    }
    }  

    onClose() {
        this.dialogRef.close();
    }

}