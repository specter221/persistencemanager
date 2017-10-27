package com.distributedpipeline.persistence.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.distributedpipeline.persistence.domain.PersistenceModel;
import com.distributedpipeline.persistence.exceptions.WorkflowAlreadyExists;
import com.distributedpipeline.persistence.exceptions.WorkflowNotFoundException;
import com.distributedpipeline.persistence.service.PersistenceService;

@RestController
@RequestMapping("/v1.0/workflow")

public class PersistenceController {
	
	    @Autowired
		private PersistenceService persistenceservice;
		
		/*----------------Get workflow ---------------------- */
		@RequestMapping(value="/get" , method=RequestMethod.GET)
		public ResponseEntity getWorkflow() throws WorkflowNotFoundException {
			List <PersistenceModel> workflow = persistenceservice.getWorkflow();	
			
			 if(workflow.isEmpty())
			 {
		        return new ResponseEntity<List<PersistenceModel>>(HttpStatus.NO_CONTENT);
		     }
		        return new ResponseEntity<List<PersistenceModel>>(workflow, HttpStatus.OK);
		   
		}

	   /* ---------------- get workflow by id -------------------------- */
		@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		    public ResponseEntity<PersistenceModel> getWorkFlow(@PathVariable("id") long id) throws WorkflowNotFoundException {
		        PersistenceModel workflow = persistenceservice.getWorkflow(id);
		        if (workflow == null) {
		            System.out.println("workflow with id " + id + " not found");
		            return new ResponseEntity<PersistenceModel>(HttpStatus.NOT_FOUND);
		        }
		        return new ResponseEntity<PersistenceModel>(workflow, HttpStatus.OK);
		    }
		
		
		 
		 /*----------------------------add workflow ----------------------- */
		@RequestMapping(value="/save", method=RequestMethod.POST, consumes="application/json")
		   public ResponseEntity addWorkflow( @RequestBody  PersistenceModel persistencemodel)
		   {
		       /*Add validation code*/
			try {
				return new ResponseEntity<PersistenceModel>(persistenceservice.addWorkflow(persistencemodel), HttpStatus.OK);
			}
			catch(Exception e) { 
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
			}
		   }
	
		
		
		/*-----------------------update workflow -----------------------*/
		@RequestMapping(value="/update", method=RequestMethod.PUT, consumes="application/json")
	    public ResponseEntity updateWorkflow(@RequestBody PersistenceModel persistencemodel) throws WorkflowAlreadyExists
	    {
	        /*Add validation code*/ 
	         PersistenceModel persistencemodel1= persistenceservice.updateWorkflow(persistencemodel);
	         if(persistencemodel1==persistencemodel) 
	         {
	             return new ResponseEntity<String>("workflow updated", HttpStatus.OK); 
	         }
	     
	         throw new WorkflowAlreadyExists("workflow already exists");
	    
	    }

		
		/*-------------- delete workflow ------------------- */
		@RequestMapping(method=RequestMethod.DELETE, value="/delete/{id}", consumes="application/json")
	    public ResponseEntity<String> deleteWorkflow(@PathVariable(value="id") int id){
	    persistenceservice.deleteWorkflow(id);
	      return new ResponseEntity<String>("Deleted succesfully", HttpStatus.OK) ;
	        
	    }
		
		
		}


