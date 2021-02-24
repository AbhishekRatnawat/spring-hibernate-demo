package com.myspring.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.myspring.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

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
					
					//create 3 student object
					System.out.println("Creating a new student object...");
					Student tempStudent1 = new Student ("John", "Doe", "JohnDoe@abc.com");
					Student tempStudent2 = new Student ("Karry", "Eod", "KarryEod@abc.com");
					Student tempStudent3 = new Student ("yrrak", "Doe", "yrrakDoe@abc.com");
					
					//start a trnx
					session.beginTransaction();
					
					//save the student object
					System.out.println("Saving the student");
					session.save(tempStudent1);
					session.save(tempStudent2);
					session.save(tempStudent3);
					
					//commit trnx
					session.getTransaction().commit();
					
					System.out.println("Done!");
				}  
				finally {
					factory.close();
				}

	}

}
