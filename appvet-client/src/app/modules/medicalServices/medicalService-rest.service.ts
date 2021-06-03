import {Injectable, Provider} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {IMedicalService, MEDICALSERVICE_SERVICE} from "./medicalService.service";
import {IServiceModel} from "./medicalService.model";

@Injectable()
export class MedicalServiceRestService implements IMedicalService {
  private url: string = `http://localhost:8080/rest/services`;

  constructor(private http: HttpClient) {
  }

  getServices(): Observable<Array<IServiceModel>> {
    return this.http.get<Array<IServiceModel>>(this.url);
  }

  saveService(service: IServiceModel): Observable<any> {
    return this.http.post(this.url, service);
  }
}

export const MedicalServiceServiceProvider: Provider = {
  provide: MEDICALSERVICE_SERVICE,
  useClass: MedicalServiceRestService
}
