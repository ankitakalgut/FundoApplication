package com.bridgelabz.toDoApplication.noteservice;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.toDoApplication.utilservice.Response;

/****************************************************************************
 @author Ankita Kalgutkar
*
*
*	
*Purpose:Controller to create api's.
******************************************************************************/

@RestController
public class NoteController 
{
	@Autowired
	NoteServiceImpl noteService;
	
	@Autowired
	NoteService service;
	
	@Autowired
	NoteRepository note;
	
	//Create an api...........
	@RequestMapping(value = "/createnote", method = RequestMethod.POST)
	public ResponseEntity<Response> createNote(@RequestBody Note note, HttpServletRequest httpServletRequest) 
	{		
		String token=httpServletRequest.getHeader("Authorization");	
		noteService.createNote(note, token);
		return new ResponseEntity<>(new Response("Note Created", HttpStatus.CREATED), HttpStatus.OK);
	}
	
	//Update api............
	@RequestMapping(value = "/updatenote", method = RequestMethod.PUT)
	public ResponseEntity<Response> updateNote(@RequestParam String noteId, @RequestParam String title, @RequestParam String description,HttpServletRequest httpServletRequest)
	{
		String token=httpServletRequest.getHeader("Authorization");	
		noteService.updateNote(noteId,title,description,token);
		return new ResponseEntity<>(new Response("Note Updated", HttpStatus.ACCEPTED), HttpStatus.OK);		
	}
	
	//Delete api..............
	@RequestMapping(value = "/deletenote", method = RequestMethod.DELETE)
	public ResponseEntity<Response> deleteNote(@RequestParam String id)
	{
		 noteService.deleteNote(id);
		return new ResponseEntity<>(new Response("Note Deleted", HttpStatus.ACCEPTED), HttpStatus.OK);		
	}
	
	//Display api........
	@RequestMapping(value = "/viewnotes", method = RequestMethod.GET)
	public List<Note> getAllNotes(HttpServletRequest httpServletRequest)
	{
		String token = httpServletRequest.getHeader("Authorization");
		System.out.println(httpServletRequest.getHeader("test"));
		List<Note> list = noteService.displayAllNotes(token);
		return list;
	}
	
	//pinned api.............
	@RequestMapping(value = "/pinnednotes", method = RequestMethod.GET)
	public List<Note> getPinnedNotes(HttpServletRequest httpServletRequest)
	{
		String token = httpServletRequest.getHeader("Authorization");
		System.out.println(httpServletRequest.getHeader("test"));
		List<Note> list = noteService.PinnedNotes(token);
		return list;
	}
	
	//Archive api...........
	@RequestMapping(value = "/archive", method = RequestMethod.GET)
	public List<Note> getArchievednotes(HttpServletRequest httpServletRequest)
	{
		String token = httpServletRequest.getHeader("Authorization");
		System.out.println(httpServletRequest.getHeader("test"));
		List<Note> list = noteService.Archive(token);
		return list;
	}	
	
	//Reminder api...........
	@RequestMapping(value = "/reminder", method = RequestMethod.POST)
	public ResponseEntity<Response> reminder(HttpServletRequest httpServletRequest,@RequestParam String id, @RequestParam String reminderTime) throws Exception
	{	String token=httpServletRequest.getHeader("Authorization");	
		Note note =noteService.setReminder(token, id, reminderTime);
		return new ResponseEntity<>(new Response("Reminder"+"-"+note.toString(), HttpStatus.ACCEPTED), HttpStatus.OK);		
	}
	
		
}