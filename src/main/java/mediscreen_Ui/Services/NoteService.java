package mediscreen_Ui.Services;

import java.util.List;

import mediscreen_Ui.Models.Note;

public interface NoteService {
	public List<Note> getAllNotes();

	public List<Note> getNotesByPatient(int patientId);

	public Note findNote(String noteId);

	public Note saveNote(Note note);

	public Note updateNote(Note note);

	public void deleteNote(String id);

}
