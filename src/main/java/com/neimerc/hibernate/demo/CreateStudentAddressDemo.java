package com.neimerc.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.neimerc.hibernate.demo.entity.Address;
import com.neimerc.hibernate.demo.entity.Student;

public class CreateStudentAddressDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.addAnnotatedClass(Address.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// create the object
			Student tempStudent = new Student("José Manuel", "Muñoz Manzano", "aaa@b.com");
			
			// create the address object
			Address homeAddress = new Address("My Street", "My City", "12345");
		
			// start a transaction
			session.beginTransaction();
			
			// save the object
			System.out.println("Saving the student and address...");
			
			tempStudent.setHomeAddress(homeAddress);		// associate the address
			session.persist(tempStudent);
		
			// commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
		} finally {
		
			// clean up code
			session.close();
			factory.close();
		}
	}

}
