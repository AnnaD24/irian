import {Observable} from "rxjs";
import {InjectionToken} from "@angular/core";
import {IServiceModel} from "./medicalService.model";


export interface IMedicalService {
  getServices(): Observable<Array<IServiceModel>>;
}

export const MEDICALSERVICE_SERVICE = new InjectionToken<IMedicalService>('MEDICALSERVICE_SERVICE');
