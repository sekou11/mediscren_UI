package mediscreen_Ui.Services.Impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mediscreen_Ui.Models.Note;
import mediscreen_Ui.Proxies.NoteProxy;

@ExtendWith(MockitoExtension.class)
class NoteServiceImplTest {

	@Mock
	private NoteProxy mockNoteProxy;

	@InjectMocks
	private NoteServiceImpl noteProxyServiceImplUnderTest;

	@Test
	void testGetAllNotes() {
		final Note note = new Note();
		note.setId("id");
		note.setPatientId(0);
		note.setNote("note");
		final List<Note> notes = Collections.singletonList(note);
		when(mockNoteProxy.getAll()).thenReturn(notes);

		final List<Note> result = noteProxyServiceImplUnderTest.getAllNotes();

		assertThat(result.size()).isEqualTo(1);
	}

	@Test
	void testGetAllNotes_NoteProxyReturnsNoItems() {
		when(mockNoteProxy.getAll()).thenReturn(Collections.emptyList());

		final List<Note> result = noteProxyServiceImplUnderTest.getAllNotes();

		assertThat(result).isEqualTo(Collections.emptyList());
	}

	@Test
	void testGetNotesByPatient() {
		final Note note = new Note();
		note.setId("id");
		note.setPatientId(0);
		note.setNote("note");
		final List<Note> notes = Arrays.asList(note);
		when(mockNoteProxy.getNotesForPatientId(0)).thenReturn(notes);

		final List<Note> result = noteProxyServiceImplUnderTest.getNotesByPatient(0);

		assertThat(result.size()).isEqualTo(1);
	}

	@Test
	void testGetNotesByPatient_NoteProxyReturnsNoItems() {
		when(mockNoteProxy.getNotesForPatientId(0)).thenReturn(Collections.emptyList());

		final List<Note> result = noteProxyServiceImplUnderTest.getNotesByPatient(0);

		assertThat(result).isEqualTo(Collections.emptyList());
	}

	@Test
	void testFindNote() {
		final Note note = new Note();
		note.setId("id");
		note.setPatientId(0);
		note.setNote("note");
		when(mockNoteProxy.findNote("id")).thenReturn(note);

		final Note result = noteProxyServiceImplUnderTest.findNote("id");

		Assertions.assertEquals(note.getId(), noteProxyServiceImplUnderTest.findNote("id").getId());
	}

	@Test
	void testSaveNote() {
		final Note noteToSave = new Note();
		noteToSave.setId("id");
		noteToSave.setPatientId(0);
		noteToSave.setNote("note");

		final Note note = new Note();
		note.setId("id");
		note.setPatientId(0);
		note.setNote("note");
		when(mockNoteProxy.saveNote(any(Note.class))).thenReturn(note);

		final Note result = noteProxyServiceImplUnderTest.saveNote(noteToSave);

		assertThat(result.getNote()).isEqualTo("note");

	}

	@Test
	void testUpdateNote() {
		final Note note = new Note();
        note.setId("id");
        note.setPatientId(0);
        note.setNote("note");

        final Note note1 = new Note();
        note1.setId("id");
        note1.setPatientId(0);
        note1.setNote("updated note");
        when(mockNoteProxy.updateNote(any(Note.class))).thenReturn(note1);

        final Note result = noteProxyServiceImplUnderTest.updateNote(note);
        assertThat (result.getNote()).isEqualTo("updated note");


	}

	@Test
	void testDeleteNote() {
		noteProxyServiceImplUnderTest.deleteNote("id");

		verify(mockNoteProxy).deleteNote("id");
	}
	
	
	
}
