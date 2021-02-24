package com.myspring.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.myspring.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Student.class)
								 .buildSessionFactory();

		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			//use the session object to save java object
			//create a student object
			System.out.println("Creating a new student object");
			Student tempStudent = new Student ("Donald", "Duck", "DDuck@abc.com");
			
			//start a trnx
			session.beginTransaction();
			
			//save the student object
			System.out.println("Saving the student");
			System.out.println(tempStudent);
			session.save(tempStudent);
			
			//commit trnx
			session.getTransaction().commit();
			
			//My new code... reading the data from DB based on the primary key
			
			//find out the student's id: PK
			System.out.println("Saved Student. Generated ID:" + tempStudent.getId());
			
			
			//get a new session and start a trnx
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//retrieve the student based on the PK
			System.out.println("\nGetting student with id: " + tempStudent.getId());
			Student myStudent = session.get(Student.class, tempStudent.getId());
			System.out.println("Get Complete: " + myStudent);
			
			//commit the trnx
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}  
		finally {
			factory.close();
		}

	}

}
