package mediscreen_Ui.Proxies;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import mediscreen_Ui.Models.Note;
@FeignClient(name = "msnote")
public interface NoteProxy {
	@GetMapping("/note/list")
    List<Note> getAll();

    @GetMapping("/note/patient/{id}")
    List<Note> getNotesForPatientId(@PathVariable int id);

    @GetMapping("/note/{id}")
    Note findNote(@PathVariable String id);

    @PostMapping("/note/add")
    Note saveNote(@Valid @RequestBody Note note);

    @PostMapping("/note/update")
    Note updateNote(@Valid @RequestBody Note noteToUpdate);

    @GetMapping("/note/delete/{id}")
    void deleteNote(@PathVariable String id);

}
