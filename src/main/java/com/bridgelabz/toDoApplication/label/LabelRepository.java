package com.bridgelabz.toDoApplication.label;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
/************************************************************************************************
 * @author Ankita Kalgutkar
 *
 * 
 * PURPOSE:LabelRepository extends all the methods of Mongo repository
 *************************************************************************************************/
@Repository
public interface LabelRepository extends MongoRepository<Label, String>
{
	public Optional<Label> findLabelByLabelname(String labelname);
}
