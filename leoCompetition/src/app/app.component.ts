import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { faMobileScreen, faRotate } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'leoCompetition';
  correctOrientation: boolean = true;

  faMobileScreen = faMobileScreen;
  faRotate = faRotate;

  curOrientation: string = "";

  constructor(public router: Router){
  }

  ngOnInit(): void {
    if(window.innerWidth < window.innerHeight){
      this.correctOrientation = false;
      this.curOrientation = "portrait"
    } else {
      this.correctOrientation = true;
      this.curOrientation = "landscape"
    }

    window.addEventListener("orientationchange", function(){
      this.window.location.reload()
    });
  }
}
