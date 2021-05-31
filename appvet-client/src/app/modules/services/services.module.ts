import {NgModule} from "@angular/core";
import { ServicesComponent } from './services/services.component';
import {ServicesRoutingModule} from "./services-routing.module";

@NgModule({
  imports: [
    ServicesRoutingModule
  ],
  declarations: [
    ServicesComponent
  ],
  providers: []
})
export class ServicesModule {
}
