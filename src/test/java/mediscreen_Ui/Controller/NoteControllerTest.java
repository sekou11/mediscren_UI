package mediscreen_Ui.Controller;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import mediscreen_Ui.Models.Note;
import mediscreen_Ui.Services.NoteService;
import mediscreen_Ui.Services.Impl.NoteServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(NoteController.class)
class NoteControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private NoteServiceImpl mockNoteProxyService;

	@Test
	void testShowAddNoteForm() throws Exception {
		final MockHttpServletResponse response = mockMvc
				.perform(get("/note/add").param("patientId", "0").accept(MediaType.TEXT_HTML)).andReturn()
				.getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}

	@Test
	void testSubmitAddNoteForm() throws Exception {
		final Note note = new Note();
		note.setId("id");
		note.setPatientId(0);
		note.setNote("note");
		when(mockNoteProxyService.saveNote(any(Note.class))).thenReturn(note);

		final MockHttpServletResponse response = mockMvc.perform(post("/note/add").param("id", "id")
				.param("patientId", "0").param("note", "note").accept(MediaType.TEXT_HTML)).andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.FOUND.value());
		verify(mockNoteProxyService).saveNote(any(Note.class));
	}

	@Test
	void testDeletePatient() throws Exception {
		final MockHttpServletResponse response = mockMvc
				.perform(get("/note/delete/{id}/{patientId}", "id", 0).accept(MediaType.TEXT_HTML)).andReturn()
				.getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.FOUND.value());
		verify(mockNoteProxyService).deleteNote("id");
	}

	@Test
	void testUpdateNote() throws Exception {
		final Note note = new Note();
		note.setId("id");
		note.setPatientId(0);
		note.setNote("note");
		when(mockNoteProxyService.findNote("id")).thenReturn(note);

		final Note note1 = new Note();
		note1.setId("id");
		note1.setPatientId(0);
		note1.setNote("note");
		when(mockNoteProxyService.updateNote(any(Note.class))).thenReturn(note1);

		final MockHttpServletResponse response = mockMvc.perform(post("/note/update/id/0")
				.accept(MediaType.APPLICATION_JSON).param("currentNote", "currentNote").accept(MediaType.TEXT_HTML))
				.andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.FOUND.value());
		verify(mockNoteProxyService).updateNote(any(Note.class));
	}

}
