import {NgModule} from '@angular/core';
import {LayoutComponent} from './layout/layout.component';
import {NavComponent} from './nav/nav.component';
import {LayoutRoutingModule} from "./layout-routing.module";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatButtonModule} from "@angular/material/button";
import {CommonsModule} from "../../commons/commons.module";


@NgModule({
  imports: [
    LayoutRoutingModule,
    MatToolbarModule,
    MatButtonModule,
    CommonsModule
  ],
  declarations: [
    LayoutComponent,
    NavComponent
  ],
  providers: []
})
export class LayoutModule {
}
