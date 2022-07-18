package mediscreen_Ui.Services.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mediscreen_Ui.Models.Note;
import mediscreen_Ui.Proxies.NoteProxy;
import mediscreen_Ui.Services.NoteService;

@Service
public class NoteServiceImpl implements NoteService {
	private static final Logger logger = LoggerFactory.getLogger(NoteServiceImpl.class);
	
	@Autowired
   private NoteProxy noteProxy;

	@Override
	public List<Note> getAllNotes() {

		return noteProxy.getAll();
	}

	@Override
	public List<Note> getNotesByPatient(int patientId) {

		return noteProxy.getNotesForPatientId(patientId);
	}

	@Override
	public Note findNote(String noteId) {

		return noteProxy.findNote(noteId);
	}

	@Override
	public Note saveNote(Note note) {

		return noteProxy.saveNote(note);
	}

	@Override
	public Note updateNote(Note note) {

		return noteProxy.updateNote(note);
	}

	@Override
	public void deleteNote(String id) {
		noteProxy.deleteNote(id);

	}

}
