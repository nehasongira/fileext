import {Injectable} from "@angular/core";
import * as SockJs from 'sockjs-client';
import * as Stomp from 'stompjs';

import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

// var SockJs = require("sockjs-client");
// var Stomp = require("stompjs");

// export interface Message {
  
//   base64Str:string;
//   ext: string;
//  }

@Injectable()
export class WebSocketService {
  private url = 'http://localhost:8080/api/files';
   private url1= 'http://localhost:8080'
 
  // messageList: Message[] = [];

    constructor(public http: HttpClient) {this.http = http; }
    saveUser(formData: FormData) {
      console.log(formData);
      return this.http.post(this.url, formData,{responseType: 'text'});
    }

    downloadFileSystem(filename: string,exten:string,lastext:string): Observable<HttpResponse<Blob>> {
      console.log("inside service");
      console.log(exten)
      console.log(filename)
      let headers = new HttpHeaders();
      headers = headers.append('Accept', exten);
      
      return this.http.get(this.url1+'/api/download/' + filename+'/'+lastext, {
        headers: headers,
        observe: 'response',
        responseType: 'blob'
      });
    }
    connect() {
        let socket = new SockJs(`http://localhost:8080/socket`);
      //  let mySocket = new WebSocket(`ws://localhost:8080/socket`);

        let stompClient = Stomp.over(socket);

        return stompClient;
    }
}
