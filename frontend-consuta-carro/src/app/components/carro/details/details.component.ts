import { Component, Input, OnInit } from '@angular/core';
import { Carro } from '../../model/carro.model';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit {

  @Input() carro: Carro;

  constructor() { }

  ngOnInit(): void {
  }

}
