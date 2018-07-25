package com.bridgelabz.toDoApplication.label;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.bridgelabz.toDoApplication.label.LabelRepository;
import com.bridgelabz.toDoApplication.noteservice.Note;
import com.bridgelabz.toDoApplication.noteservice.NoteRepository;
import com.bridgelabz.toDoApplication.userservice.model.User;
import com.bridgelabz.toDoApplication.userservice.repository.UserRepository;
import com.bridgelabz.toDoApplication.utilservice.JWToken;
import com.bridgelabz.toDoApplication.utilservice.NoteExceptionHandler;
import com.bridgelabz.toDoApplication.utilservice.UserExceptionHandler;

/************************************************************************************************
 * @author Ankita Kalgutkar
 *
 * 
 * PURPOSE:Method Implementation
 *************************************************************************************************/
@Service
public class LabelServiceImpl implements LabelService
{
	@Autowired
	JWToken jwToken;
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	NoteRepository noteRepository;
	
	@Autowired
	LabelRepository labelRepository;
	
	public void createlabel(Label label, String token)
	{
		String email = jwToken.verifyToken(token);
		User user = userRepository.findByEmail(email);
		if (user == null)
			throw new UserExceptionHandler("Please login");
		if (label == null)
			throw new NoteExceptionHandler("Note cannot be created with empty title and description");
		List<Label> list=labelRepository.findAll();
		for(Label l:list)
		{
			if(l.getLabelname().equals(	label.getLabelname()))	
			throw new UserExceptionHandler("Label already exists........");
		}
		label.setUserId(user.getId());
		label.setLabelname(label.getLabelname());
		try 
		{
			labelRepository.save(label);	
		}
		catch (DataIntegrityViolationException | ConstraintViolationException e)
		{
			throw new UserExceptionHandler("label cannot be created due to database error, please try again later");
		}
	}
	
	//Add Label
	public void addlabel(String noteid, String labelname, String token) 
	{
		String email = jwToken.verifyToken(token);
		User user = userRepository.findByEmail(email);
		if (user == null)
		throw new UserExceptionHandler("Please login");
		List<Label> list=labelRepository.findAll();
		for(Label l:list)
		{
			if(l.getLabelname().equals(labelname))
			{
				Optional<Note> optionalnote = noteRepository.findById(noteid);
				if (!optionalnote.isPresent())
				{
					throw new UserExceptionHandler("Note not found");
				}
				Note note=optionalnote.get();
				note.setLabelname(labelname);
				noteRepository.save(note);	
			}
			else
				throw new UserExceptionHandler("First create teh label");
		}
	}
	
	//Delete label method...........
	public void deletelabel(String labelid) 
	{
		Optional<Label> optionalnote = labelRepository.findById(labelid);
		if (!optionalnote.isPresent())
		{
			throw new UserExceptionHandler("label not Found");
		}	
		labelRepository.deleteById(labelid);
	}
	
	//update label......
	public void updateLabel(String labelid,String labelname,String token) 
	{
		String email = jwToken.verifyToken(token);
		User user = userRepository.findByEmail(email);
		if (user == null)
			throw new UserExceptionHandler("Please login.........");
		Optional<Label> optionalnote = labelRepository.findById(labelid);
		if (!optionalnote.isPresent())
		{
			throw new NoteExceptionHandler("Label not found....");
		}
		Label label=optionalnote.get();
		label.setLabelname(labelname);
		labelRepository.save(label);		
	}	
	
	//Display the dispaly the notes by label
	public List<Note> displayAllNotes(String labelname, String token)
	{
		String email=jwToken.verifyToken(token);
		User user=userRepository.findByEmail(email);
		String userId=user.getId();
		List<Note> noteList = noteRepository.findNotesByUserId(userId);
		List<Note> labelnotes= new  ArrayList<Note>();
		for(Note n:noteList)
		{
			if(n.getLabelname().equals(labelname))
			{
				labelnotes.add(n);
			}
		}
		return labelnotes;		
	}	
}


