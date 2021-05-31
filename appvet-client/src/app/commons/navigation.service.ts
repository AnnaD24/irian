import {Injectable, InjectionToken, Provider} from "@angular/core";
import {ActivatedRoute, Router} from "@angular/router";

export interface INavigationService {
  openAppointments(): Promise<boolean>;

  openServices(): Promise<boolean>;

  getParam(activatedRoute, paramName: string): string;

  getQueryParam(activatedRoute, paramName: string): string;
}

@Injectable()
export class NavigationService implements INavigationService {

  constructor(private router: Router) {
  }

  openAppointments(): Promise<boolean> {
    return this.router.navigate(['appointments']);
  }

  openServices(): Promise<boolean> {
    return this.router.navigate(['services']);
  }

  getParam(activatedRoute: ActivatedRoute, paramName: string): string {
    return activatedRoute.snapshot.params[paramName];
  }

  getQueryParam(activatedRoute: ActivatedRoute, paramName: string): string {
    return activatedRoute.snapshot.queryParams[paramName];
  }
}

export const NAVIGATION_SERVICE: InjectionToken<INavigationService> = new InjectionToken('NAVIGATION_SERVICE');

export const NavigationServiceProvider: Provider = {
  provide: NAVIGATION_SERVICE,
  useClass: NavigationService
};
