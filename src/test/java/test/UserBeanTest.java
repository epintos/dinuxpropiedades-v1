package test;

import org.junit.Assert;
import org.junit.Test;

import ar.edu.itba.it.paw.services.beans.User;
import ar.edu.itba.it.paw.services.exceptions.BadInformationException;
import ar.edu.itba.it.paw.services.exceptions.InformationMissingException;

public class UserBeanTest {
	
	@Test
	public void testLongName() {
		//username with more than 50 characters
		String name = "asdfasdfadfadfasdfasdfafasdfasdfasdfasdfsadfasdfasdfsadfdafsafsdfsadfadfasfasfasd";
		String surname = "test";
		String email = "test@test.com";
		String username = "test";
		String password = "test";
		String phone = "4-123-4567";
		
		try {
			User u = new User(name, surname, email, username, password, phone);
		} catch (BadInformationException e) {
			return;
		} catch (Exception e) {
			Assert.fail("Deberia fallar porque el nombre del usuario es muy largo");
		}
		Assert.fail();
		
	}
	
	@Test
	public void testLongSurnameName() {
		//username with more than 50 characters
		String surname = "asdfasdfadfadfasdfasdfafasdfasdfasdfasdfsadfasdfasdfsadfdafsafsdfsadfadfasfasfasd";
		String name = "test";
		String email = "test@test.com";
		String username = "test";
		String password = "test";
		String phone = "4-123-4567";
		
		try {
			new User(name, surname, email, username, password, phone);
		} catch (BadInformationException e) {
			return;
		} catch (Exception e) {
			Assert.fail("Deberia fallar porque el apellido del usuario es muy largo");
		}
		Assert.fail();
		
	}
	
	@Test
	public void testLongEmail() {
		//username with more than 50 characters
		String surname = "test";
		String name = "test";
		String email = "testasdfasfasdfasdfasdfasdfasdfasdfdasfadsfasdfasdfasdfsadfasdfdsafsadfsadfdsafdsaf@test.com";
		String username = "test";
		String password = "test";
		String phone = "4-123-4567";
		
		try {
			new User(name, surname, email, username, password, phone);
		} catch (BadInformationException e) {
			return;
		} catch (Exception e) {
			Assert.fail("Deberia fallar porque el mail del usuario es muy largo");
		}
		Assert.fail();
		
	}
	
	@Test
	public void testLongPassword() {
		//username with more than 50 characters
		String surname = "test";
		String name = "test";
		String email = "test@test.com";
		String username = "test";
		String password = "testasdfasdfasdfasfasdfasdfadsfasfasfasdfdsafadsfdfsafasdfasfasfasfasfsadfasdfasdfasdfadsfads";
		String phone = "4-123-4567";
		
		try {
			new User(name, surname, email, username, password, phone);
		} catch (BadInformationException e) {
			return;
		} catch (Exception e) {
			Assert.fail("Deberia fallar porque el password del usuario es muy largo");
		}
		Assert.fail();
		
	}
	
	@Test
	public void testLongUsername() {
		//username with more than 50 characters
		String surname = "test";
		String name = "test";
		String email = "test@test.com";
		String username = "testasdfasdfsadfasdfasdfadsfsadfasdfdasfadsfasdfasdfasdfasdfasdfasdfasdfasdfsdfasdfasdfasdfsdafads";
		String password = "test";
		String phone = "4-123-4567";
		
		try {
			User u = new User(name, surname, email, username, password, phone);
		} catch (BadInformationException e) {
			return;
		} catch (Exception e) {
			Assert.fail("Deberia fallar porque username es muy largo");
		}
		Assert.fail();
		
	}
	
	@Test
	public void testEmptyName() {
		//username with more than 50 characters
		String name = "";
		String surname = "test";
		String email = "test@test.com";
		String username = "test";
		String password = "test";
		String phone = "4-123-4567";
		
		try {
			User u = new User(name, surname, email, username, password, phone);
		} catch (InformationMissingException e) {
			return;
		} catch (Exception e) {
			Assert.fail("Deberia fallar porque el nombre del usuario es vacio");
		}
		Assert.fail();
		
	}
	
	@Test
	public void testEmtpySurname() {
		//username with more than 50 characters
		String surname = "";
		String name = "test";
		String email = "test@test.com";
		String username = "test";
		String password = "test";
		String phone = "4-123-4567";
		
		try {
			User u = new User(name, surname, email, username, password, phone);
		} catch (InformationMissingException e) {
			return;
		} catch (Exception e) {
			Assert.fail("Deberia fallar porque el apellido del usuario es vacio");
		}
		Assert.fail();
		
	}
	
	@Test
	public void testEmptyEmail() {
		//username with more than 50 characters
		String surname = "test";
		String name = "test";
		String email = "";
		String username = "test";
		String password = "test";
		String phone = "4-123-4567";
		
		try {
			User u = new User(name, surname, email, username, password, phone);
		} catch (InformationMissingException e) {
			return;
		} catch (Exception e) {
			Assert.fail("Deberia fallar porque el mail del usuario es vacio");
		}
		Assert.fail();
		
	}
	
	@Test
	public void testEmptyPassword() {
		//username with more than 50 characters
		String surname = "test";
		String name = "test";
		String email = "test@test.com";
		String username = "test";
		String password = "";
		String phone = "4-123-4567";
		
		try {
			User u = new User(name, surname, email, username, password, phone);
		} catch (InformationMissingException e) {
			return;
		} catch (Exception e) {
			Assert.fail("Deberia fallar porque el password del usuario es vacio");
		}
		Assert.fail();
		
	}
	
	@Test
	public void testEmptyUsername() {
		//username with more than 50 characters
		String surname = "test";
		String name = "test";
		String email = "test@test.com";
		String username = "";
		String password = "test";
		String phone = "4-123-4567";
		
		try {
			User u = new User(name, surname, email, username, password, phone);
		} catch (InformationMissingException e) {
			return;
		} catch (Exception e) {
			Assert.fail("Deberia fallar porque el nombre de usuario es vacio");
		}
		Assert.fail();
		
	}


}
