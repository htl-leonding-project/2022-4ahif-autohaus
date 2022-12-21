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

  constructor(public router: Router){
  }

  ngOnInit(): void {
    if(screen.orientation.type == "portrait-secondary" || screen.orientation.type == "portrait-primary"){
      this.correctOrientation = false;
    } else {
      this.correctOrientation = true;
    }

    window.addEventListener("orientationchange", function(){
      this.window.location.reload()
    });
  }
}
