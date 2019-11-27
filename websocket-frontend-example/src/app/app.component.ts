import {Component} from '@angular/core';
//import $ from 'jquery';
import * as $ from "jquery";
import {WebSocketService} from "./services/websocket.service";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent {
   
 private base64string:String="abc";
  private base64str:String=" ";
    public notifications = 0;
   private stompClient;
   private message: string;
   private name: string;
 
    constructor(private webSocketService: WebSocketService) {

        this.stompClient = this.webSocketService.connect();

        this.stompClient.connect({}, frame => {

            this.stompClient.subscribe('/chat',(message) => {
                if(message.body) {

                  // var reader = new FileReader();
                  // reader.readAsDataURL(blob); 
                  // reader.onloadend = function() {
                  //     var base64data = reader.result;                
                  //     console.log(base64data);
                  // }
                  //message.body="";
                  console.log("whyyyy");
                 // console.log(message.body);
                  var binary = atob(message.body.replace(/\s/g, ''));
                    var len = binary.length;
                    var buffer = new ArrayBuffer(len);
                    var view = new Uint8Array(buffer);
                    for (var i = 0; i < len; i++) {
                        view[i] = binary.charCodeAt(i);
                    }
                          var blob = new Blob( [view],{ type: 'image/pdf' } || {type: 'file/jpeg'});
                          console.log(blob.size);
                          var download = document.querySelector( "a[ download ]" );
                          var downloadUrl = URL.createObjectURL(blob);
                          download.setAttribute( "href", downloadUrl );

                    //link.href = URL.createObjectURL(blob);
                  // $(".chat").append("<div class='message'>"+message.body+"</div>")
                  console.log(message.body);
                }
              })

        });

        
        
    }
    handleFileSelect(evt){
      
      var f=evt.target.files[0];
      var fileName = evt.target.files[0].name;
      var reader=new FileReader();
     //var arrayoffile=reader.readAsArrayBuffer(f);
    //console.log(arrayoffile.length);
      let self = this;
      reader.onload=(function(theFile){
        let me = self;
        return function(e){
          var binaryData=e.target.result;
          var base64string=window.btoa(binaryData);
         // document.getElementById('base64').innerHTML = base64string;
          //  console.log(base64string);
           me.message = base64string;
            me.name=fileName;
          //  console.log(me.message)
          
        }
        
      })
       //this.sendMessage(this.base64string);
      (f);
      reader.readAsBinaryString(f);
     // console.log(this.base64string);
      //this.sendMessage(this.base64string);
    }


 
    
    sendMessage(message){
     // console.log('inside method');
      console.log(this.message)
      // console.log(this.base64string);
     // var base64str=message;
     
        this.stompClient.send("/app/send/message" , {}, this.message);

    //    // console.log(message);
    //     //  $('#input').val('');
        } 
        
        time(){
        var now = new Date();
        let date: Date= new Date(now.getFullYear(), now.getMonth(), now.getDate(), 10, 0, 0, 0) - now;
        if (millisTill10 < 0) {
             millisTill10 += 86400000; // it's after 10am, try 10am tomorrow.
        }
        setTimeout(function(){alert("It's 10am!")}, millisTill10);
      }


        notifyMe() {
          var name="hellllll"
          if (!("Notification" in window)) {
              console.log('Browser does not support notifications.');
          } else {
            const p: string = Notification['permission'];
              // check if permission is already granted
              if (p === 'granted') {
                  // show notification here
                  var notify = new Notification(name, {
                      body: 'How are you doing?',
                      
                  });

              } else {
                  // request permission from user
                  Notification.requestPermission().then(function (p) {
                      if (p === 'granted') {
                          // show notification here
                          var notify = new Notification('Hi there!', {
                              body: 'How are you doing?',
                              icon: 'https://bit.ly/2DYqRrh',
                          });
                      } else {
                          console.log('User blocked notifications.');
                      }
                  }).catch(function (err) {
                      console.error(err);
                  });
              }
          }
      }
    
}
