package com.myspring.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.myspring.hibernate.demo.entity.Student;

public class CreateStudentDemo {

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
			Student tempStudent = new Student ("John", "Doe", "JohnDoe@abc.com");
			
			//start a trnx
			session.beginTransaction();
			
			//save the student object
			System.out.println("Saving the student");
			session.save(tempStudent);
			
			//commit trnx
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}  
		finally {
			factory.close();
		}

	}

}
