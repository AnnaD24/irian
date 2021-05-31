import {IServiceModel} from "../services/service.model";
import {Status} from "./appointment-status.model";

export interface IAppointment {
  _id?: string,
  doctorName: string,
  petName: string,
  diagnostic: string,
  status: Status,
  dateTime: Date,
  services: Array<IServiceModel>,
  totalCost: number
}
