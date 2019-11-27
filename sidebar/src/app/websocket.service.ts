import {Injectable} from "@angular/core";
import * as SockJs from 'sockjs-client';
import * as Stomp from 'stompjs';
import { HttpClient } from '@angular/common/http';

// var SockJs = require("sockjs-client");
// var Stomp = require("stompjs");

// export interface Message {
  
//   base64Str:string;
//   ext: string;
//  }

@Injectable()
export class WebSocketService {
  private url = 'http://localhost:8080/api/files';
 
 
  // messageList: Message[] = [];

    constructor(public http: HttpClient) {this.http = http; }
    saveUser(formData: FormData) {
      console.log(formData);
      return this.http.post(this.url, formData,{responseType: 'text'});
    }
    connect() {
        let socket = new SockJs(`http://localhost:8080/socket`);
      //  let mySocket = new WebSocket(`ws://localhost:8080/socket`);

        let stompClient = Stomp.over(socket);

        return stompClient;
    }
}
