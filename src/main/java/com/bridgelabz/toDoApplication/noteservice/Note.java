package com.bridgelabz.toDoApplication.noteservice;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import io.swagger.annotations.ApiModelProperty;
/*********************************************************************************
 * @author Ankita Kalgutkar
 *
 * 
 *PURPOSE:Note Entity class.
 ************************************************************************************/

@Document(collection="Notes")
public class Note 
{	
	@Id
	@ApiModelProperty(hidden = true)
	private String  id;
	
	private String title;
	
	private String pin;
	
	private String description;
	
	private String archive;
	
	@ApiModelProperty(hidden = true)
	private String createdDate;
	
	@ApiModelProperty(hidden = true)
	private String userId;
	
	@ApiModelProperty(hidden = true)
	private boolean trash=true;	
	
	@ApiModelProperty(hidden=true)
	private String labelname;

	public String getLabelname() 
	{
		return labelname;
	}

	public void setLabelname(String labelname) 
	{
		this.labelname = labelname;
	}

	public String getArchive()
	{
		return archive;
	}

	public void setArchive(String archive)
	{
		this.archive = archive;
		
	}
	
	
	public String getPin()
	{
		return pin;
	}

	public void setPin(String pin) 
	{
		this.pin = pin;
	}

	public boolean isTrash() 
	{
		return trash;
	}

	public String getId() 
	{
		return id;
	}

	public void setId(String id) 
	{
		this.id = id;
	}

	public void setTrash(boolean trash) 
	{
		this.trash = trash;
	}

	public String getTitle() 
	{
		return title;
	}

	public Note()
	{
		
	}
	public void setTitle(String title) 
	{
		this.title = title;
	}
	
	public String getDescription() 
	{
		return description;
	}
	
	public void setDescription(String description) 
	{
		this.description = description;
	}

	public String getCreatedDate() 
	{
		return createdDate;
	}

	public void setCreatedDate(String createdDate )
	{
		this.createdDate = createdDate;
	}
	
	public String getUserId()
	{
		return userId;
	}
	
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Note [id=" + id + ", title=" + title + ", pin=" + pin + ", description=" + description + ", archive="
				+ archive + ", createdDate=" + createdDate + ", userId=" + userId + ", trash=" + trash + "]";
	}

}