/**
 * EventFacadeBean Stateless SessionBean Class
 * 
 * @author jperezsalva@uoc.edu
 * @version 1.0
 */

package ejb;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.Context;
import javax.naming.NamingException;

import jpa.*;

@Stateless
public class EventFacadeBean implements EventFacade, EventFacadeRemote {

	private static final String ALGORITHM = "md5";
	private static final String DIGEST_STRING = "HG58YZ3CR9";
	private static final String CHARSET_UTF_8 = "utf-8";
	private static final String SECRET_KEY_ALGORITHM = "DESede";
	private static final String TRANSFORMATION_PADDING = "DESede/CBC/PKCS5Padding";

	@PersistenceContext(unitName = "eagenda")
	private EntityManager entman;

	/**
	 * Method used to send a Comment about an Event.
	 */
	public int sendComment(String user, int eventId, String comment) {

		Long id = new Long(eventId);

		int error = 0;

		Event event = entman.find(Event.class, id);
		User usuario = entman.find(User.class, user);

		if ((event != null) && (usuario != null)) {

			Comment comentario = new Comment(comment, event, usuario);

			entman.persist(comentario);

			event.setKeyword(updateKeywords(id));
			entman.merge(event);

		} else {

			error = 2;
		}

		return error;
	}

	/**
	 * Method used to add rattings about an Event.
	 */
	public int addRatting(String user, int eventId, Integer ratting) {

		Long id = new Long(eventId);

		int error = 0;

		String criteria = "SELECT r FROM Ratting r JOIN FETCH r.event e JOIN FETCH r.user u WHERE r.event.id = :id AND r.user.nif = ?1";
		TypedQuery<Ratting> query = entman.createQuery(criteria, Ratting.class);
		query.setParameter("id", id);
		query.setParameter(1, user);

		if (query.getResultList().size() > 0)
			return 1;

		Event event = entman.find(Event.class, id);
		User usuario = entman.find(User.class, user);

		if ((event != null) && (usuario != null)) {

			Ratting evaluation = new Ratting(ratting, event, usuario);

			entman.persist(evaluation);

			event.setKeyword(updateKeywords(id));
			entman.merge(event);

		} else {

			error = 2;
		}

		return error;
	}

	/**
	 * Method used to return the ratting average.
	 */
	public int avgRatting(int eventId) {

		Long id = new Long(eventId);

		String criteria = "SELECT DISTINCT r FROM Ratting r JOIN FETCH r.event e JOIN FETCH r.user u WHERE r.event.id = :id";
		TypedQuery<Ratting> query = entman.createQuery(criteria, Ratting.class);
		query.setParameter("id", id);

		int contador = 0, sum = 0;

		for (Ratting r : query.getResultList()) {
			sum += r.getRatting();
			contador++;
		}

		return (contador > 0) ? Math.round((float) sum / contador) : 0;
	}

	/**
	 * Method used to suggest an Event to your friend by mail.
	 */
	public int suggest(int eventId, String email) {

		int errors = 0;

		Event event = showEvent(eventId);
		User user = showUser(email.trim());

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		try {

			int[] values = new int[] { 114, 111, 111, 116, 49, 50, 51, 52 };

			String text = "";

			for (int i = 0; i < values.length; i++)
				text += (char) values[i];

			final String codedPassword = encrypt(text);
			final String decodedPassword = decrypt(codedPassword);

			Session session = Session.getDefaultInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(
									"eagenda3layers@gmail.com", decodedPassword);

						}
					});

			try {

				final Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("eagenda3layers@gmail.com"));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(email.trim()));
				message.setSubject("Suggest Calendar Event");
				message.setText("Dear friend,"
						+ "\n\n Your good friend thought you may want to know"
						+ "\n about this upcoming event:" + "\n\n\n Name: "
						+ event.getName() + "\n Description: "
						+ event.getDescription() + "\n Street: "
						+ event.getAddress().getStreet() + "\n City: "
						+ event.getAddress().getCity() + "\n State: "
						+ event.getAddress().getState() + "\n Country: "
						+ event.getAddress().getCountry() + "\n Zip Code: "
						+ event.getAddress().getZipCode() + "\n Init Date: "
						+ event.getInitDate().getTime() + "\n End Date: "
						+ event.getEndDate().getTime() + "\n Company: "
						+ event.getCompany().getName());

				Runnable r = new Runnable() {
					@Override
					public void run() {
						try {
							Transport.send(message);
						} catch (MessagingException ex) {
						}
					}
				};

				Thread t = new Thread(r);
				t.start();

			} catch (MessagingException e1) {

				errors = 2;
			}
		} catch (Exception e2) {
		}

		return errors;
	}

	/**
	 * Method used to add an Event to favorites.
	 */
	public int addToFavorites(String user, int eventId) {

		Long id = new Long(eventId);

		int error = 1;

		String criteria = "SELECT DISTINCT u FROM User u LEFT JOIN u.favoriteEvents f WHERE u.nif = ?1 AND f.id = :id";
		TypedQuery<User> query = entman.createQuery(criteria, User.class);
		query.setParameter("id", id);
		query.setParameter(1, user);

		if (query.getResultList().size() > 0)
			return 1;

		User usuario = entman.find(User.class, user);
		Event event = entman.find(Event.class, id);

		if ((usuario != null) && (event != null)) {

			usuario.getFavoriteEvents().add(event);
			event.getUser().add(usuario);

			entman.persist(usuario);
			entman.persist(event);

			event.setKeyword(updateKeywords(id));
			entman.merge(event);

			error = 0;

		} else {

			error = 2;
		}

		return error;
	}

	/**
	 * Method used to return a collection of favorite events.
	 */
	public Collection<Event> listAllFavorites(String user)
			throws NoResultException {

		String criteria = "SELECT u FROM User u WHERE u.nif = ?1";
		TypedQuery<User> query = entman.createQuery(criteria, User.class);
		query.setParameter(1, user);

		User usuario = query.getSingleResult();

		Collection<Event> listAllFavorites = new HashSet<Event>();

		for (Event event : usuario.getFavoriteEvents()) {
			listAllFavorites.add(event);
		}

		return listAllFavorites;

	}

	/**
	 * Method used to list all events in ascending order.
	 */
	public Collection<Event> listAllEvents() {

		String criteria = "SELECT DISTINCT e FROM Event e ORDER BY e.name, e.initDate ASC";

		TypedQuery<Event> query = entman.createQuery(criteria, Event.class);
		Collection<Event> listAllEvents = query.getResultList();

		return listAllEvents;

	}

	/**
	 * Method used to find events by category.
	 */
	public Collection<Event> findEventsByCategory(int categoryId) {

		String criteria = "SELECT DISTINCT e FROM Event e JOIN FETCH e.categories ORDER BY e.id ASC";

		TypedQuery<Event> query = entman.createQuery(criteria, Event.class);

		Collection<Event> listAllEvents = query.getResultList();
		Collection<Event> listEventsByCategory = new HashSet<Event>();

		for (Event event : listAllEvents) {

			Set<Category> categories = event.getCategories();

			Iterator<Category> iterator = categories.iterator();

			for (int i = 0; iterator.hasNext(); i++) {
				if (categoryId == iterator.next().getId())
					listEventsByCategory.add(event);

			}
		}

		return listEventsByCategory;

	}

	/**
	 * Method used to find events by name.
	 */
	public Collection<Event> findEventsByName(String name) {

		String criteria = "SELECT DISTINCT e FROM Event e WHERE LOWER(e.name) LIKE :name ORDER BY e.name, e.initDate ASC";

		TypedQuery<Event> query = entman.createQuery(criteria, Event.class);
		Collection<Event> eventsByName = query.setParameter("name",
				name.toLowerCase()).getResultList();

		return eventsByName;

	}

	/**
	 * Method used to find events by keyword.
	 */
	public Collection<Event> findEventsByWord(String word) {

		Collection<Event> listAllEvents = listAllEvents();
		Collection<Event> listEventsByWord = new HashSet<Event>();

		for (Event event : listAllEvents) {

			Set<Keyword> listKeywords = event.getKeyword();
			Iterator<Keyword> iterator = listKeywords.iterator();

			for (int i = 0; iterator.hasNext(); i++) {

				Keyword keyword = iterator.next();

				if (keyword.getKeyword().equalsIgnoreCase(word)) {
					listEventsByWord.add(event);
				}
			}
		}

		return listEventsByWord;
	}

	/**
	 * Method used to show an Event.
	 */
	public Event showEvent(int eventId) throws NullPointerException {

		Long id = new Long(eventId);

		try {

			String criteria = "SELECT DISTINCT e FROM Event e WHERE e.id = :id";
			TypedQuery<Event> query = entman.createQuery(criteria, Event.class);
			Event event = query.setParameter("id", id).getSingleResult();

			return event;

		} catch (NoResultException exception) {

			return null;
		}
	}

	/**
	 * Method used to show an User.
	 */
	public User showUser(String email) throws NullPointerException {

		try {

			String criteria = "SELECT DISTINCT u FROM User u WHERE u.email = :email";
			TypedQuery<User> query = entman.createQuery(criteria, User.class);
			User user = query.setParameter("email", email).getSingleResult();

			return user;

		} catch (NoResultException exception) {

			return null;
		}
	}

	/**
	 * Method used to delete keyword duplicates from Events.
	 */
	public Set<Keyword> updateKeywords(Long eventId) {

		Event event = entman.find(Event.class, eventId);

		Set<Keyword> words = new HashSet<Keyword>(event.getKeyword());
		Map<String, String> map = new HashMap<String, String>();

		Iterator<Keyword> iterator = words.iterator();

		while (iterator.hasNext()) {
			String word = iterator.next().getKeyword();
			map.put(word, word);
		}

		words.clear();

		Iterator<String> iter = map.keySet().iterator();

		while (iter.hasNext())
			words.add(new Keyword(iter.next()));

		return words;
	}

	/**
	 * Method used to decrypt stored encrypted mail password.
	 */
	public String decrypt(String message) throws Exception {

		final MessageDigest md = MessageDigest.getInstance(ALGORITHM);

		final byte[] digestOfPassword = md.digest(DIGEST_STRING
				.getBytes(CHARSET_UTF_8));

		final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

		for (int i = 0, j = 16; i < 8;)
			keyBytes[j++] = keyBytes[i++];

		final SecretKey key = new SecretKeySpec(keyBytes, SECRET_KEY_ALGORITHM);

		final IvParameterSpec iv = new IvParameterSpec(new byte[8]);

		final Cipher decipher = Cipher.getInstance(TRANSFORMATION_PADDING);

		decipher.init(Cipher.DECRYPT_MODE, key, iv);

		final byte[] plainText = decipher.doFinal(message.getBytes());

		return new String(plainText, CHARSET_UTF_8);
	}

	public String encrypt(String message) throws Exception {

		final MessageDigest md = MessageDigest.getInstance(ALGORITHM);

		final byte[] digestOfPassword = md.digest(DIGEST_STRING
				.getBytes(CHARSET_UTF_8));

		final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

		for (int j = 0, k = 16; j < 8;)
			keyBytes[k++] = keyBytes[j++];

		final SecretKey key = new SecretKeySpec(keyBytes, SECRET_KEY_ALGORITHM);

		final IvParameterSpec iv = new IvParameterSpec(new byte[8]);

		final Cipher cipher = Cipher.getInstance(TRANSFORMATION_PADDING);

		cipher.init(Cipher.ENCRYPT_MODE, key, iv);

		final byte[] plainTextBytes = message.getBytes(CHARSET_UTF_8);

		final byte[] cipherText = cipher.doFinal(plainTextBytes);

		return new String(cipherText);
	}
}
