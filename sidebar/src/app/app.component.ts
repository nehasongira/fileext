import { Component } from '@angular/core';
import { WebSocketService } from './websocket.service';
import { Links } from './links';
import { DomSanitizer } from '@angular/platform-browser';
export interface Message {
  
  base64Str:string;
  ext1: string;
  fileName1:string;
 }

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})


export class AppComponent {
  private base64string: String = "abc";
  private base64str: String = " ";
  public notifications = 0;
  private stompClient;
  private message: string;
  private ext: string;
  private fileName:string;
  private name: string;
  links: Links[] = [];
  constructor(
    private webSocketService: WebSocketService,
    private sanitizer: DomSanitizer
  ) {

    this.stompClient = this.webSocketService.connect();

    this.stompClient.connect({}, frame => {

      this.stompClient.subscribe('/chat', (message) => {
        if (message.body) {

          var fileobj=JSON.parse(message.body);
          var basestring=fileobj.base64Str;
          var exten=fileobj.ext1;
          this.name=fileobj.fileName1;
          console.log(this.name);
          //message.body="";
          console.log("whyyyy");
          // console.log(message.body);
         // var binary = atob(message.body.replace(/\s/g, ''));
         var binary = atob(basestring.replace(/\s/g, ''));
          var len = binary.length;
          var buffer = new ArrayBuffer(len);
          var view = new Uint8Array(buffer);
          for (var i = 0; i < len; i++) {
            view[i] = binary.charCodeAt(i);
          }
          var blob = new Blob([view],{type: exten || 'application/octet-stream'});
          console.log(blob.size);
          console.log(blob.type);
          var download = document.querySelector("a[ download ]");
          var downloadUrl = URL.createObjectURL(blob);
          //////////////////////////////////////////////////

            //           var reader = new FileReader();
            // reader.readAsDataURL(blob); 
            // reader.onloadend = function() {
            //     var base64data = reader.result;                
            //     console.log(base64data);
            // }
            console.log(downloadUrl);
           this.links.push({ links: this.sanitizer.bypassSecurityTrustUrl(downloadUrl),file_name:this.name,extension:exten });
          //  //download.setAttribute( "href", downloadUrl );
        console.log(message.body);
        }
      })

    });
}

  handleFileSelect(evt) {

    var f = evt.target.files[0];
    this.ext=evt.target.files[0].type;
    this.fileName = evt.target.files[0].name;
    var reader = new FileReader();
    let self = this;
    reader.onload = (function (theFile) {
      let me = self;
      return function (e) {
        var binaryData = e.target.result;
        var base64string = window.btoa(binaryData);
        me.message = base64string;
      }

    })
      
      (f);
    reader.readAsBinaryString(f);
   
  }

  // clickAndDisable(link) {
  //   // disable subsequent clicks
  //   link.onclick = function(event) {
  //      event.preventDefault();
  //   }
  // }   

  sendMessage(message) {
    // console.log('inside method');
    console.log(this.message)
    console.log(this.ext);
    const message1: Message = {
       base64Str:this.message,
       ext1: this.ext,
       fileName1:this.fileName,
     
    };

   // this.stompClient.send("/app/send/message", {}, this.message);
   this.stompClient.send("/app/send/message", {}, JSON.stringify(message1));
   }
}
