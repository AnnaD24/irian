import {Observable} from "rxjs";
import {InjectionToken} from "@angular/core";
import {IServiceModel} from "./medicalService.model";


export interface IMedicalService {
  getServices(): Observable<Array<IServiceModel>>;

  saveService(service: IServiceModel): Observable<any>;
}

export const MEDICALSERVICE_SERVICE = new InjectionToken<IMedicalService>('MEDICALSERVICE_SERVICE');
