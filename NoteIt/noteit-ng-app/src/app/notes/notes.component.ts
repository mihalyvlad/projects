import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Notebook} from "./model/Notebook";
import {ApiService} from "../shared/api.service";
import {Note} from "./model/note";

@Component({
  selector: 'app-notes',
  templateUrl: './notes.component.html',
  styleUrls: ['./notes.component.css']
})
export class NotesComponent implements OnInit {
  notebooks: Notebook[] = [];
  notes: Note[] = [];
// cand un user apasa pe un notebook trebuie sa prelucram aceasta variabila;
  selectedNotebook: Notebook;
  searchText: string;

//injection http module
  constructor(private apiService: ApiService) {
  }

  ngOnInit() {
    //le iau pe cele construite default in backend
    this.getAllNotebooks();
    this.getAllNotes();

  }

  public getAllNotebooks() {

    this.apiService.getAllNotebooks().subscribe(
      res => {
        this.notebooks = res;

      },
      error => {
        alert("o eroare la getALLnotebooks");

      }
    );

  }

  getAllNotes() {
    this.apiService.getAllNotes().subscribe(
      res => {
        this.notes = res
      },
      err => {
        alert("eroare in afisarea Note-urilor")
      }
    );
  }

  createNotebook() {
    let newNotebook: Notebook = {
      name: 'New notebook',
      id: null,
      nbOfNotes: 0
    }
    this.apiService.postNotebook(newNotebook).subscribe(
      res => {
        //initial id e null, dar dupa ce se salveaza ii atribuim id ce vine de la Server si apoi si apui imi pun acest newbook in lista mea de notebooks
        newNotebook.id = res.id;
        this.notebooks.push(newNotebook)
      },
      err => {
        alert("eroare in timpul salvarii notebook ului")
      }
    );
  }

  updateNotebook(updatedNotebook: Notebook) {
    this.apiService.postNotebook(updatedNotebook).subscribe(
      res => {


      },
      err => {
        alert("eroare in updateului de notebook ")
      }
    );
  }

  deleteNotebook(notebook: Notebook) {
    if (confirm("are you sure you want to delete the notebook?"))
    //trebuie notebook.id deoarece in Service am pus un id care l concatenez la URL sa sterg un notebook in functie de index
    //vreau sa mi l scoata si din notebooks, iau indexul si il folosesc splice(index, cate sa stearga)
      this.apiService.deleteNotebook(notebook.id).subscribe(
        res => {
          let indexOfNotebook = this.notebooks.indexOf(notebook);
          this.notebooks.splice(indexOfNotebook, 1);
        },
        err => {
          alert("something bad happend during the deleting")
        }
      )

  }

  deleteNote(note: Note) {
    if(confirm("are you sure you want to delete the note?"))
      this.apiService.deleteNote(note.id).subscribe(
        res => {
          let indexOfNote = this.notes.indexOf(note);
          this.notes.splice(indexOfNote, 1);
        },
        err => {
          alert("something bad happend during the deleting of the note")
        }
      )

  }

  createNote(notebookId: string) {
    let newNote: Note;
    newNote = {
      id: null,
      title: "New Note",
      text: "Write some text",
      lastModifiedOn: null,
      // not null , trebuie sa punem Note ul intr un notebook,deci apelam create de note in fct de un notebookId
      notebookId: notebookId
    };
    this.apiService.saveNote(newNote).subscribe(
      res => {
        newNote.id = res.id;
        this.notes.push(newNote);
      },
      err => {
        alert("eroare la salvarea de Note")
      }
    );


  }

  selectNotebook(notebook: Notebook) {
    this.selectedNotebook = notebook;
    this.apiService.getNotesByNotebook(notebook.id).subscribe(
      res => {
        this.notes = res;
      },
      err => {
        alert("An error has occurred while downloading the notes;")
      }
    );
  }

  updateNote(updatedNote: Note) {
    this.apiService.saveNote(updatedNote).subscribe(
      res => {

      },
      err => {
        alert("An error has occurred while downloading the notes;")
      }
    );
  }

  selectAllNotes() {
    this.selectedNotebook=null
    this.getAllNotes();

  }
}
