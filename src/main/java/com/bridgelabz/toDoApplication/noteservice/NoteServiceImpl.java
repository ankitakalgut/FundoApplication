package com.bridgelabz.toDoApplication.noteservice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import javax.validation.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.bridgelabz.toDoApplication.userservice.model.User;
import com.bridgelabz.toDoApplication.userservice.repository.UserRepository;
import com.bridgelabz.toDoApplication.utilservice.JWToken;
import com.bridgelabz.toDoApplication.utilservice.NoteExceptionHandler;
import com.bridgelabz.toDoApplication.utilservice.UserExceptionHandler;

/*********************************************************************************
 * @author Ankita Kalgutkar
 *
 * 
 *PURPOSE:Methods to perform note operations
 ************************************************************************************/
@Service
public class NoteServiceImpl implements NoteService 
{
	@Autowired
	NoteRepository noteRepository;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	UserRepository userRepository;

	@Autowired
	JWToken jwToken;
	
	//Method to create a Note..........................
	@Override
	public void createNote(Note note, String token) 
	{
		String email = jwToken.verifyToken(token);
		User user = userRepository.findByEmail(email);
		if (user == null)
			throw new UserExceptionHandler("Please login");
		if (note.getTitle() == null && note.getDescription() == null)
			throw new NoteExceptionHandler("Note cannot be created with empty title and description");
		note.setCreatedDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
		note.setUserId(user.getId());
		try 
		{
			noteRepository.save(note);
		}
		catch (DataIntegrityViolationException | ConstraintViolationException e)
		{
			throw new NoteExceptionHandler("Note cannot be created due to database error, please try again later");
		}
	}

	//Method to delete a Note......................
	@Override 
	public void deleteNote(String noteId)
	{
		Optional<Note> optionalnote = noteRepository.findById(noteId);

		if (!optionalnote.isPresent())
		{
			throw new NoteExceptionHandler("User not Found");
		}
		if (!optionalnote.get().isTrash())
		{
			throw new NoteExceptionHandler("Note is not present in trash");
		}	
			noteRepository.deleteById(noteId);
	}
	
	//Method to display all Note of a user......................
	@Override
	public List<Note> displayAllNotes(String token) 
	{
		String email = jwToken.verifyToken(token);
		User user = userRepository.findByEmail(email);
		String userId = user.getId();
		List<Note> noteList = noteRepository.findNotesByUserId(userId);
		return noteList;
	}	
	
	//Method to get important notes...................
	@Override
	public List<Note>PinnedNotes(String token)
	{
		
		String email=jwToken.verifyToken(token);
		User user=userRepository.findByEmail(email);
		String userId=user.getId();
		List<Note> noteList = noteRepository.findNotesByUserId(userId);
		List<Note> pinnedNotes= new  ArrayList<Note>();
		for(Note n:noteList)
		{
			if(n.getPin().equals("yes"))
			{
			 pinnedNotes.add(n);
			}
		}
		return pinnedNotes;		
	}
	
	//Method to Update a particular note...............
	public void updateNote(String noteId,String title,String description,String token) 
	{
		String email = jwToken.verifyToken(token);
		User user = userRepository.findByEmail(email);
		if (user == null)
			throw new UserExceptionHandler("Please login.........");
		Optional<Note> optionalnote = noteRepository.findById(noteId);
		if (!optionalnote.isPresent())
		{
			throw new NoteExceptionHandler("User not Found");
		}
		Note note=optionalnote.get();
		note.setTitle(title);
		note.setDescription(description);
		noteRepository.save(note);		
	}
	
	//Method to get archive note
	public List<Note> Archive(String token)
	{
		String email=jwToken.verifyToken(token);
		User user=userRepository.findByEmail(email);
		String userId=user.getId();
		List<Note> noteList = noteRepository.findNotesByUserId(userId);
		List<Note> archive= new  ArrayList<Note>();
		for(Note n:noteList)
		{
			if(n.getPin().equals("yes"))
			{
			 archive.add(n);
			}
		}
		return archive;		
	}	
	
	//Method to set Reminder for note
	@Override
	public Note setReminder(String token, String id, String reminderTime) throws Exception  
	{	
	   // Optional<Note> note = Preconditions.checkNotNull(noteRepository.findById(id), "No notes found");
	   Timer timer = null;
	   Optional<Note> note = noteRepository.findById(id);
	   if (note.isPresent()) 
	   {
	      Date reminder = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(reminderTime);
	      long timeDifference = reminder.getTime() - new Date().getTime();
	      timer = new Timer();
	      timer.schedule(new TimerTask() 
	      {
	          @Override
	          public void run() 
	          {
	             System.out.println("Reminder task:" + note.toString());

	           }
	        }, timeDifference);
	     }
	     else
	     {
	        throw new NoteExceptionHandler("Note id..........");
	     }
	   	 return note.get();
	 }
}
