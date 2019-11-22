import {Injectable} from "@angular/core";
import * as SockJs from 'sockjs-client';
import * as Stomp from 'stompjs';

// var SockJs = require("sockjs-client");
// var Stomp = require("stompjs");

// export interface Message {
  
//   base64Str:string;
//   ext: string;
//  }

@Injectable()
export class WebSocketService {

  // messageList: Message[] = [];

    constructor() { }

    connect() {
        let socket = new SockJs(`http://localhost:8080/socket`);
      //  let mySocket = new WebSocket(`ws://localhost:8080/socket`);

        let stompClient = Stomp.over(socket);

        return stompClient;
    }
}
