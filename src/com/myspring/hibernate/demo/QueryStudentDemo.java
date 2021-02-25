package com.myspring.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.myspring.hibernate.demo.entity.Student;



public class QueryStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Student.class)
								 .buildSessionFactory();

		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			
			//start a trnx
			session.beginTransaction();
			
			//Query Students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			//display the students
			displayStudents(theStudents);
			
			//query students: lastName = 'Doe'
			theStudents = session.createQuery("from Student s where s.lastName='Doe'").getResultList(); //lastName is our class variable not table attribute
			
			//display students
			displayStudents(theStudents); //converted into function by  going into source --> extractFunction
			
			
			//query students: lastName='Doe' or firstname= "Duck"
			theStudents = session.createQuery("from Student s where s.lastName='Doe' OR s.firstName='Donald'").getResultList();
			System.out.println("OR clause output");
			displayStudents(theStudents);
			
			//commit trnx
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}  
		finally {
			factory.close();
		}

	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
