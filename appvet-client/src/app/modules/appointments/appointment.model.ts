import {IServiceModel} from "../medicalServices/medicalService.model";
import {Status} from "./appointment-status.model";

export interface IAppointment {
  id?: string,
  doctorName?: string,
  petName?: string,
  diagnostic?: string,
  status?: Status,
  dateTime?: Date,
  services?: Array<IServiceModel>,
  totalCost?: number
}
