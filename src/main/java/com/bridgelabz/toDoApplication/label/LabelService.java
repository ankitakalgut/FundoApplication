package com.bridgelabz.toDoApplication.label;

import java.util.List;
import com.bridgelabz.toDoApplication.noteservice.Note;

/************************************************************************************************
 * @author Ankita Kalgutkar
 *
 * 
 * PURPOSE:Interface class
 *************************************************************************************************/

public interface LabelService 
{
	public void createlabel(Label label, String token);	
	public void addlabel(String noteid,String labelname,String token);
	public void deletelabel(String labelid) ;
	public void updateLabel(String labelid,String labelname,String token);
	public List<Note> displayAllNotes(String labelname, String token);
}
