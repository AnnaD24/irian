import {IAppointment} from "./appointment.model";

export interface IPage {
  content: Array<IAppointment>
  totalElements: number
}
