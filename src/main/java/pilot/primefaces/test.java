package pilot.primefaces;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Client client = createClient("dina","saleh","salah@gmail");
		Client client1 = createClient("mina","adel","mina@yahoo.com");

		BankEmployee employee = createBankEmployee();
		Account fromAcc = createAccount(client);
		Account toAcc = createAccount(client1);
		Transaction transaction = createTransaction(client,fromAcc,toAcc);
		boolean transfered = transaction.transfer(fromAcc, toAcc, 10);
		System.out.println(transfered);
		Bank bank = createBank(client,toAcc);
		session.save(client);
		session.save(client1);
		session.save(employee);
		session.save(fromAcc);
		session.save(toAcc);
		session.save(transaction);
		session.save(bank);
		session.getTransaction().commit();
		session.close();
	}

	public static Client createClient(String name,String pass,String mail)
	{
		Client client = new Client();
		client.setName(name);
		client.setPassword(pass);
		client.setConfirmPassword(pass);
		client.setMail(mail);
		client.setAddress(name+" 123456");
		//client.setDate(new Date());
		client.setMobile("0114741200534");
		client.setNetSalary(1200456);
		return client;
	}
	
	public static BankEmployee createBankEmployee()
	{
		BankEmployee employee = new BankEmployee();
		employee.setName("amr mohamed salah");
		employee.setPosition("senior");
		return employee;
	}
	
	public static Account createAccount(Client client)
	{
		Account account = new Account();
		Random random = new Random();
        int randomNumber = random.nextInt(901) + 100;
		account.setAmount(500);
		account.setClient(client);
		return account;
	}
	
	public static Transaction createTransaction(Client client,Account senderAcc , Account recieverAcc)
	{
		Transaction transaction = new Transaction();
		transaction.setClient(client);
		transaction.setDescription("first tansaction");
		transaction.setFromAccount(senderAcc);
		transaction.setToAccount(recieverAcc);
		//transaction.setTransactionDate(null);
		return transaction;
	}
	
	public static Bank createBank(Client client,Account account)
	{
		Bank bank = new Bank();
		bank.setAddress("1 mohamed abd el 5alee2 - ain shams");
		bank.setAccount(account);
		bank.setClient(client);
		return bank;
	}

}
