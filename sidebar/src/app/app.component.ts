import { Component, ViewChild, ElementRef } from '@angular/core';
import {FormGroup} from  '@angular/forms';
import { WebSocketService } from './websocket.service';
import { Links } from './links';
import { DomSanitizer } from '@angular/platform-browser';
import { FormsModule}  from '@angular/forms';
import {FileUploader} from 'ng2-file-upload';
import * as fileSaver from 'file-saver';

export interface Message {
  
  fromid:string;
  toid:string;
  fileName1:string;
  ext:string;
  lastext:string;
 }

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})


export class AppComponent {
   public userFile:any =File;
   fileSystemName: string;
  // @ViewChild('fileInput') fileInput: ElementRef;
 
  // uploader: FileUploader;
  // isDropOver: boolean;

  private base64string: String = "abc";
  private base64str: String = " ";
  public notifications = 0;
  private stompClient;
  private exten:string;
  private message: string;
  private ext: string;
  private fileName:string;
  private name: string;
  private fromId: string="abc";
  private toId: string="efg";
  private lastext :string;
  private lastext1 :string;
  links: Links[] = [];
  private fid:string;
  constructor(
    private webSocketService: WebSocketService,
    private sanitizer: DomSanitizer
  ) {

    this.stompClient = this.webSocketService.connect();

    this.stompClient.connect({}, frame => {

      this.stompClient.subscribe('/chat', (message) => {
        if (message.body) {
           var fileobj=JSON.parse(message.body);
          this.exten=fileobj.ext;
          this.name=fileobj.fromid+"_"+fileobj.toid+"_"+fileobj.fileName1; 
          this.fid=fileobj.fromid;
          this.lastext1=fileobj.lastext;
          console.log(this.name);
          console.log("jhbefjnd");



        //   var fileobj=JSON.parse(message.body);
        //   var basestring=fileobj.base64Str;
        //   var exten=fileobj.ext1;
        //   this.name=fileobj.fileName1;
        //   console.log(this.name);
        //   //message.body="";
        //   console.log("whyyyy");
        //   // console.log(message.body);
        //  // var binary = atob(message.body.replace(/\s/g, ''));
        //  var binary = atob(basestring.replace(/\s/g, ''));
        //   var len = binary.length;
        //   var buffer = new ArrayBuffer(len);
        //   var view = new Uint8Array(buffer);
        //   for (var i = 0; i < len; i++) {
        //     view[i] = binary.charCodeAt(i);
        //   }
        //   var blob = new Blob([view],{type: exten || 'application/octet-stream'});
        //   console.log(blob.size);
        //   console.log(blob.type);
        //   var download = document.querySelector("a[ download ]");
        //   var downloadUrl = URL.createObjectURL(blob);
        //   //////////////////////////////////////////////////

        //     //           var reader = new FileReader();
        //     // reader.readAsDataURL(blob); 
        //     // reader.onloadend = function() {
        //     //     var base64data = reader.result;                
        //     //     console.log(base64data);
        //     // }
        //     console.log(downloadUrl);
        //    this.links.push({ links: this.sanitizer.bypassSecurityTrustUrl(downloadUrl),file_name:this.name,extension:exten });
        //   //  //download.setAttribute( "href", downloadUrl );
        // console.log(message.body);
         }
      }
      )

    });
}
fileupload(event)
{
  const file=event.target.files[0];
  this.ext=event.target.files[0].type;
  this.fileName = event.target.files[0].name;
  var dotPosition = this.fileName.indexOf(".");
  this.lastext =this.fileName.substring(dotPosition, this.fileName.length);
  //const lastext=event.target.files[0].split('.').pop();
  
  this.userFile=file;
}

downloadFileSystem() {
  console.log("inside downloadfile");
  this.webSocketService.downloadFileSystem(this.name,this.ext,this.lastext)
    .subscribe(response => {
      const filename = response.headers.get('fileName');
      const ext2=response.headers.get('exten');
      console.log("shhh")
      console.log(filename);
      console.log(ext2);
      this.saveFile(response.body,'application/pdf');
      //this.saveToFileSystem(event,'application/pdf');
    });
}
// saveToFileSystem(response, type) {
//   const contentDispositionHeader: string = response.headers.get('Content-Disposition');
//   const show:string=response.headers.get('fileName');
//   const parts: string[] = contentDispositionHeader.split(';');
//   const filename = parts[1].split('=')[1];
//   const blob = new Blob([response.body], {
//       type: type
//   });
//   // saveAs(blob, filename);
// }

saveFile(data: any, filename?: string, ext3? : string) {
  
  const blob = new Blob([data], {type: ext3 || 'application/octet-stream'});
  console.log(blob);
  console.log(ext3);
  var download = document.querySelector("a[ download ]");
  var downloadUrl = URL.createObjectURL(blob);
  console.log(downloadUrl);
  //this.links.push({ links: this.sanitizer.bypassSecurityTrustUrl(downloadUrl),file_name:filename,extension:ext3 });
  download.setAttribute( "href", downloadUrl );
  //fileSaver.saveAs(blob, filename);
}

sendMessage(message) {
  const message1: Message = {
    fileName1:this.fileName,
     fromid:this.fromId,
     toid:this.toId,
     ext:this.ext,
     lastext:this.lastext,
 };
 const formData=new FormData();
 formData.append('file',this.userFile);
 formData.append('fromid',this.fromId);
 formData.append('toid',this.toId);
 formData.append('filename',this.fileName);
 formData.append('ext',this.ext);
 formData.append('lastext',this.lastext);
 this.webSocketService.saveUser(formData).subscribe(res => {
  console.log(res);
})
// this.stompClient.send("/app/send/message", {}, this.message);
 this.stompClient.send("/app/send/message", {}, JSON.stringify(message1));
}
  handleFileSelect(evt) {

    var f = evt.target.files[0];
    this.ext=evt.target.files[0].type;
    this.fileName = evt.target.files[0].name;
    var size=evt.target.files[0].size;
  this.fromId="abc";
    this.toId="efg";
    console.log("sdgvfb")
    console.log(size);

    var reader = new FileReader();
    let self = this;
    reader.onload = (function (theFile) {
      let me = self;
      return function (e) {
        // if(size>5000000)
        // {
        //   confirm("Please enter within 5mb size");
         
        // }
        // else{
          var binaryData = e.target.result;
          var base64string = window.btoa(binaryData);
          me.message = base64string;
          console.log(base64string.length);
        // }
        
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

 
}
