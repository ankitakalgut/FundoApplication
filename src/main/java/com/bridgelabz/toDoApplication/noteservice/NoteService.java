package com.bridgelabz.toDoApplication.noteservice;

import java.util.List;

/*********************************************************************************
 * @author Ankita Kalgutkar
 *
 * 
 *PURPOSE:Methods to carry out service
 *********************************************************************************/
public interface NoteService
{
	public void createNote(Note note, String token);

	public List<Note> displayAllNotes(String token);

	void deleteNote(String noteId);

	public List<Note> PinnedNotes(String pin);

	public void updateNote(String noteId, String title, String description,String token);
	
	public List<Note> Archive(String token);

	public Note setReminder(String token, String id, String reminderTime) throws Exception;
}