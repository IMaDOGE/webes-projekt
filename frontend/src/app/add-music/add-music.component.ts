import { Component, OnInit } from '@angular/core';
import { MusicService } from '../music.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import { Music } from '../music';

@Component({
  selector: 'app-add-music',
  templateUrl: './add-music.component.html',
  styleUrls: ['./add-music.component.css']
})
export class AddMusicComponent implements OnInit {

  constructor(private musicservice: MusicService) { }

  music: Music = new Music();
  submitted = false;

  songsaveform = new FormGroup({
    musicTitle: new FormControl('' , [Validators.required, Validators.minLength(1) ] ),
    musicAuthor: new FormControl('', [Validators.required, Validators.minLength(1)]),
    musicAlbum: new FormControl('', [Validators.required, Validators.minLength(1)]),
    musicGenre: new FormControl('', [Validators.required, Validators.minLength(1)]),
    musicLength: new FormControl('', [Validators.required, Validators.minLength(1)])
  });

  ngOnInit() {
    this.submitted = false;
  }

  saveSong(saveSong) {
    this.music = new Music();
    this.music.musicTitle = this.SongTitle.value;
    this.music.musicAuthor = this.SongAuthor.value;
    this.music.musicAlbum = this.SongAlbum.value;
    this.music.musicGenre = this.SongGenre.value;
    this.music.musicLength = this.SongLength.value;
    this.submitted = true;
    this.save();
  }

  save() {
    this.musicservice.createSong(this.music)
      .subscribe(data => console.log(data), error => console.log(error));
    this.music = new Music();
  }

  get SongTitle() {
    return this.songsaveform.get('musicTitle');
  }

  get SongAuthor() {
    return this.songsaveform.get('musicAuthor');
  }

  get SongAlbum() {
    return this.songsaveform.get('musicAlbum');
  }

  get SongGenre() {
    return this.songsaveform.get('musicGenre');
  }

  get SongLength() {
    return this.songsaveform.get('musicLength');
  }

  addSongForm() {
    this.submitted = false;
    this.songsaveform.reset();
  }
}

