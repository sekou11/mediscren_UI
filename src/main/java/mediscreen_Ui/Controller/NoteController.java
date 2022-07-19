package mediscreen_Ui.Controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mediscreen_Ui.Models.Note;
import mediscreen_Ui.Services.Impl.NoteServiceImpl;

@Controller
public class NoteController {
	 private static final Logger logger = LoggerFactory.getLogger(NoteController.class);
	 @Autowired
	private NoteServiceImpl noteService;
	 
	 @GetMapping("/note/add")
	    public String showAddNoteForm(@RequestParam int patientId, Model model){
	        logger.info("GET /addNote patientId : " + patientId);
	        Note note = new Note();
	        note.setPatientId(patientId);
	        model.addAttribute("currentNote", note);
	        return "note/noteForm";
	    }
	 
	 @PostMapping("/note/add")
	    public String submitAddNoteForm(@ModelAttribute @Valid Note note, BindingResult bindingResult, Model model) {
	        if (!bindingResult.hasErrors()) {
	            logger.info("patientID = " + note.getPatientId());
	            noteService.saveNote(note);
	            model.addAttribute("notes", note);
	            return "redirect:/patient/fiche/" + note.getPatientId();
	        }

	        return "note/add";
	    }
	 
	 
	 

	    
	    @GetMapping("/note/update/{id}")
	    public String showUpdateNoteForm(@PathVariable("id") String id, Model model) {
	        model.addAttribute("currentNote", noteService.findNote(id));
	        return "note/update";
	    }

	    @PostMapping("/note/update/{id}/{patientId}")
	    public String updateNote(@PathVariable String id, @ModelAttribute("currentNote") Note currentNote, Model model) {
	        // get note from database by id
	        Note existingNote = noteService.findNote(id);
	        existingNote.setNote(currentNote.getNote());

	        //save updated note
	        noteService.updateNote(existingNote);
	        return "redirect:/patient/fiche/{patientId}";
	    }
	    
	    @GetMapping("/note/delete/{id}/{patientId}")
	    public String deletePatient(@PathVariable String id, @PathVariable int patientId) {
	        noteService.deleteNote(id);
	        return "redirect:/patient/fiche/{patientId}";
	    }


}
