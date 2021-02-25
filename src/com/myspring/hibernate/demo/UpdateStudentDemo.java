package com.myspring.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.myspring.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Student.class)
								 .buildSessionFactory();

		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			
			int studentId = 1;
			//start a trnx
			session.beginTransaction();
			
			//save the student object
			System.out.println("Getting student with ID: " + studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
			System.out.println("Get complete: " + myStudent);
			
			System.out.println("Updating the student...");
			myStudent.setFirstName("ScoobyDoo");
			
			//commit trnx
			session.getTransaction().commit();
			System.out.println("checking the update " + myStudent);
			
			//bulk update
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			session.createQuery("update Student set email='foo@gmail.com'").executeUpdate();
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}  
		finally {
			factory.close();
		}

	}

}
