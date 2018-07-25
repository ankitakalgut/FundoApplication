package com.bridgelabz.toDoApplication.label;

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
import com.bridgelabz.toDoApplication.noteservice.Note;
import com.bridgelabz.toDoApplication.utilservice.Response;

/************************************************************************************************
 * @author Ankita Kalgutkar
 *
 * 
 * PURPOSE:Controller for label
 *************************************************************************************************/
@RestController
public class LabelController 
{
	@Autowired
	LabelService labelservice;
	@Autowired 
	LabelRepository repository;
	@Autowired
	LabelServiceImpl serviceimpl;
	
	//create label.........
	@RequestMapping(value = "/createlabel", method = RequestMethod.POST)
	public ResponseEntity<Response> createlabel(@RequestBody Label label,HttpServletRequest httpServletRequest)
	{
		String token = httpServletRequest.getHeader("Authorization");
		System.out.println(httpServletRequest.getHeader("test"));
		serviceimpl.createlabel(label,token);
		return new ResponseEntity<>(new Response("Label Created", HttpStatus.CREATED), HttpStatus.OK);
	}
	
	//add label to note....
	@RequestMapping(value = "/addlabel", method = RequestMethod.POST)
	public ResponseEntity<Response> addlabel(@RequestParam String noteid,@RequestParam String labelname,HttpServletRequest httpServletRequest)
	{
		String token = httpServletRequest.getHeader("Authorization");
		System.out.println(httpServletRequest.getHeader("test"));
		serviceimpl.addlabel(noteid,labelname,token);
		return new ResponseEntity<>(new Response("Label added.....", HttpStatus.CREATED), HttpStatus.OK);
	}
	
	//Delete api..............
	@RequestMapping(value = "/deletelabel", method = RequestMethod.DELETE)
	public ResponseEntity<Response> deletelabel(@RequestParam String labelid)
	{
		serviceimpl.deletelabel(labelid);
		return new ResponseEntity<>(new Response("Label Deleted", HttpStatus.ACCEPTED), HttpStatus.OK);		
	}
		
	//Update label api...........
	@RequestMapping(value = "/updatelabel", method = RequestMethod.PUT)
	public ResponseEntity<Response> updateNote(@RequestParam String labelid, @RequestParam String labelname,HttpServletRequest httpServletRequest)
	{
		String token=httpServletRequest.getHeader("Authorization");	
		serviceimpl.updateLabel(labelid,labelname,token);
		return new ResponseEntity<>(new Response("label  Updated", HttpStatus.ACCEPTED), HttpStatus.OK);		
	}
		
	//Display api........
	@RequestMapping(value = "/NotesBylabelname", method = RequestMethod.GET)
	public List<Note> getAllNotes(@RequestParam String labelname,HttpServletRequest httpServletRequest)
	{
		String token = httpServletRequest.getHeader("Authorization");
		System.out.println(httpServletRequest.getHeader("test"));
		List<Note> list = serviceimpl.displayAllNotes(labelname,token);
		return list;
	}	
}
