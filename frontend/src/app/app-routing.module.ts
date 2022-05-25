import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddMusicComponent } from './add-music/add-music.component';
import {MusicListComponent} from './music-list/music-list.component';

const routes: Routes = [
  { path: '', redirectTo: 'view-song', pathMatch: 'full' },
  { path: 'view-song', component: MusicListComponent},
  { path: 'add-song', component: AddMusicComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
