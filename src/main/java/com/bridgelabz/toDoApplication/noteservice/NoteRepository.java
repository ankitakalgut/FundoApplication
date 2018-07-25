package com.bridgelabz.toDoApplication.noteservice;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
/*********************************************************************************
 * @author Ankita Kalgutkar
 *
 * 
 *PURPOSE:Repository class to extend the MongoRepository
 ************************************************************************************/
@Repository
public interface NoteRepository extends MongoRepository<Note, String>
{
	public List<Note> findNotesByUserId(int userId);
	public List<Note> findNotesByUserId(String userId);
	public Note findByTitle(String id);
	public List<Note> findByPin(String pin);
}