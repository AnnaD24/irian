import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LayoutComponent} from './layout/layout.component';

const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [
      {
        path: 'services', loadChildren: () => import('../services/services.module').then(m => m.ServicesModule)
      },
      {
        path: 'appointments', loadChildren: () => import('../appointments/appointments.module').then(m => m.AppointmentsModule)
      },
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LayoutRoutingModule {
}
