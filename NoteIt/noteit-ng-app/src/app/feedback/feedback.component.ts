import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ApiService} from "../shared/api.service";

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css']
})
export class FeedbackComponent implements OnInit {
model:FeedbackViewModel={
  //default, nu vreau sa am nulls
  name:'',
  email:'',
  feedback:''
}
// cand aceasta componenta e init primim o instanta HTTP
  constructor(private apiService:ApiService) { }

  ngOnInit() {
  }
    sendFeedback():void{
  //din backend

  this.apiService.postFeedback(this.model).subscribe(
    //succes
    res=>{
      location.reload();

    },
    //eroare
    err=>
      alert("o EROARE")

  );
}


}
export interface FeedbackViewModel {
  name:string;
  email:string;
  feedback:string;
  //1:1 cu ce am in backend la FVM
}
