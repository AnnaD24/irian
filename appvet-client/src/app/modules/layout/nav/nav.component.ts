import {Component, Inject} from '@angular/core';
import {INavigationService, NAVIGATION_SERVICE} from "../../../commons/navigation.service";

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent {

  constructor(@Inject(NAVIGATION_SERVICE) private navigationService: INavigationService) {
  }

  goToAppointments() {
    this.navigationService.openAppointments();
  }

  goToServices() {
    this.navigationService.openServices();
  }

}
