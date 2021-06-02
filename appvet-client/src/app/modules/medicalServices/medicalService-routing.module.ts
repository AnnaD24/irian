import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MedicalServiceListComponent} from "./medicalService-list/medicalService-list.component";

const routes: Routes = [
  {path: '', component: MedicalServiceListComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MedicalServiceRoutingModule {
}
