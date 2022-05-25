import { Component, OnInit } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import {FormControl, FormGroup } from '@angular/forms';
import { MusicService } from '../music.service';
import { Music } from '../music';

@Component({
  selector: 'app-music-list',
  templateUrl: './music-list.component.html',
  styleUrls: ['./music-list.component.css']
})
export class MusicListComponent implements OnInit {

  constructor(private musicservice: MusicService) { }

  songsArray: any[] = [];
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject();


  songs: Observable<Music[]>;
  music: Music = new Music();
  deleteMessage = false;
  songlist: any;
  isupdated = false;

  musicupdateform = new FormGroup({
    musicId: new FormControl(),
    musicTitle: new FormControl(),
    musicAuthor: new FormControl(),
    musicAlbum: new FormControl(),
    musicGenre: new FormControl(),
    musicLength: new FormControl()
  });

  ngOnInit() {
    this.isupdated = false;
    this.dtOptions = {
      pageLength: 6,
      stateSave: true,
      lengthMenu: [[6, 16, 20, -1], [6, 16, 20, 'All']],
      processing: true
    };
    this.musicservice.getSongList().subscribe(data => {
      this.songs = data;
      this.dtTrigger.next();
    });
  }

  deleteSong(id: number) {
    this.musicservice.deleteSong(id)
      .subscribe(
        data => {
          console.log(data);
          this.deleteMessage = true;
          this.musicservice.getSongList().subscribe(data => {
            this.songs = data;
          });
        },
        error => console.log(error));
  }

  updateSong(id: number) {
    this.musicservice.getSong(id)
      .subscribe(
        data => {
          this.songlist = data;
        },
        error => console.log(error));
  }

  updateSong2(updatesong) {
    this.music = new Music();
    this.music.musicId = this.SongId.value;
    this.music.musicTitle = this.SongTitle.value;
    this.music.musicAuthor = this.SongAuthor.value;
    this.music.musicAlbum = this.SongAlbum.value;
    this.music.musicGenre = this.SongGenre.value;
    this.music.musicLength = this.SongLength.value;

    this.musicservice.updateSong(this.music.musicId, this.music).subscribe(
      data => {
        this.isupdated = true;
        this.musicservice.getSongList().subscribe(data => {
          this.songs = data;
        });
      },
      error => console.log(error));
  }

  get SongId() {
    return this.musicupdateform.get('musicId');
  }

  get SongTitle() {
    return this.musicupdateform.get('musicTitle');
  }

  get SongAuthor() {
    return this.musicupdateform.get('musicAuthor');
  }

  get SongAlbum() {
    return this.musicupdateform.get('musicAlbum');
  }

  get SongGenre() {
    return this.musicupdateform.get('musicGenre');
  }

  get SongLength() {
    return this.musicupdateform.get('musicLength');
  }

  changeisUpdate() {
    this.isupdated = false;
  }
}
