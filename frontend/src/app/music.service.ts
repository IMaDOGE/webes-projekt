import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class MusicService {

  private baseUrl = 'http://localhost:8080/';

  constructor(private http: HttpClient) { }

  createSong(music: object): Observable<object> {
    return this.http.post(`${this.baseUrl}` + 'songs', music);
  }

  getSong(id: number): Observable<object> {
    return this.http.get(`${this.baseUrl}/songs/${id}`);
  }

  getSongList(): Observable<any> {
    return this.http.get(`${this.baseUrl}` + 'songs');
  }

  updateSong(id: number, value: any): Observable<object> {
    return this.http.post(`${this.baseUrl}/update-song/${id}`, value);
  }

  deleteSong(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/songs/${id}`, { responseType: 'text' });
  }
}
